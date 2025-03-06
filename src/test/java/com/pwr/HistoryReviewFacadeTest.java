package com.pwr;

import com.pwr.model.*;
import com.pwr.presenter.HistoryReviewFacade;
import com.pwr.view.IClientView;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryReviewFacadeTest{
    @Mocked
    private RequestDAO requestDAO;
    @Mocked
    private ReviewDAO reviewDAO;
    @Mocked
    private IClientView clientHistoryView;
    private HistoryReviewFacade historyReviewFacade;

    @BeforeEach
    void setUp(){
        historyReviewFacade = new HistoryReviewFacade(requestDAO, reviewDAO, clientHistoryView);
    }
    static Stream<Arguments> repairHistoryParameters(){
        return Stream.of(
                Arguments.of(List.of(new Request(1, "Issue 1", "Device1", null,  Status.PENDING, null, null)), false),
                Arguments.of(List.of(
                        new Request(1, "Issue1", "Device1", null, Status.IN_PROGRESS, null,null),
                        new Request(2, "Issue2", "Device2", null, Status.COMPLETED, null,null)
                ), true),
                Arguments.of(List.of(new Request(1,"Issue 1", "Device1", null,  Status.COMPLETED, null, null)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("repairHistoryParameters")
    void testGenerateRepairHistory(List<Request> requests, boolean expectedResult){
        new Expectations() {{
           requestDAO.getAllRequests(); result = requests;

           for(Request request : requests){
               if(request.getStatus() == Status.COMPLETED){
                   reviewDAO.isReviewExist(request.getId()); result = false;
               }
           }
        }};

        boolean result = historyReviewFacade.generateRepairHistory();

        assertEquals(expectedResult,result);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 5, 'Excellent service', true", // repairId, rate, descriptionOfRepair, shouldAddReview
            "2, 3, 'Medium quality', true",
            "0, 0, null, false"
    })
    void testAddReviewAboutRepair(int repairId, int rate, String descriptionOfRepair, boolean shouldAddReview) {
        new Expectations() {{
            // Simulating client input based on test parameters
            clientHistoryView.getClientChoice(anyString); result = shouldAddReview;
            if (shouldAddReview) {
                clientHistoryView.getSelectedRepairId(); result = repairId;
                clientHistoryView.enterReviewRate(); result = rate;
                clientHistoryView.enterReviewDescription(); result = descriptionOfRepair;

                Request request = new Request(repairId, "issueDescription", "Model", null, Status.COMPLETED, null, null);
                requestDAO.getRequestById(repairId); result = request;
            }
        }};

        historyReviewFacade.addReviewAboutRepair();

        new Verifications() {{
            if (shouldAddReview) {
                // Ensure client inputs are gathered and the request is fetched
                clientHistoryView.getSelectedRepairId(); times = 1;
                clientHistoryView.enterReviewRate(); times = 1;
                clientHistoryView.enterReviewDescription(); times = 1;
                requestDAO.getRequestById(repairId); times = 1;
                Request request = new Request(repairId, "issueDescription", "Model", null, Status.COMPLETED, null, null);

            } else {
                clientHistoryView.getSelectedRepairId(); times = 0;
                clientHistoryView.enterReviewRate(); times = 0;
                clientHistoryView.enterReviewDescription(); times = 0;
                requestDAO.getRequestById(repairId); times = 0;
            }
        }};
    }


}
