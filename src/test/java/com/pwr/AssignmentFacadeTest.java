package com.pwr;

import com.pwr.model.*;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(IgnoreNullPointerExceptionExtension.class)
public class AssignmentFacadeTest {

    // @Mocked - mockowane wszystkie instancji tej klasy
    // @Injectable - mockowana tylko instancja nad ktorej jest adnotacja

    @Tested
    private AssignmentFacade assignmentFacade; // Testowana klasa (JMockit automatycznie jej zainicjuje)

    @Injectable
    private ClientDAO clientDAO;

    @Injectable
    private RequestDAO requestDAO; // Pole, ktore bedzie mockowane

    @Injectable
    private TechnicianDAO technicianDAO;

    @Injectable
    private IAdminView adminView;

    @Injectable
    private IClientView clientView;

    @Injectable
    private ITechnicianView technicianView;

    public AssignmentFacadeTest() {
    }

    @ParameterizedTest
    @MethodSource("provideRequestsForSendNewRequests")
    void sendNewRequestsTest(List<Request> allRequests, boolean expectedResult){
        new Expectations(){{
           requestDAO.getAllRequests(); result = allRequests;
        }};

        boolean testResult = assignmentFacade.sendNewRequests();


        assertEquals(expectedResult, testResult);
    }

    private static Stream<Arguments> provideRequestsForSendNewRequests() {
        return Stream.of(
                Arguments.of(null, false), // Przypadek null
                Arguments.of(Collections.emptyList(), false), // Pusta lista
                Arguments.of(
                        List.of(
                                new Request(1, "Issue1", "Model1", null, Status.NEW, null, null),
                                new Request(2, "Issue2", "Model2", null, Status.ASSIGNED, null, null),
                                new Request(3, "Issue3", "Model3", null, Status.IN_PROGRESS, null, null)
                        ),
                        true // Jeden request z NEW
                ),
                Arguments.of(
                        List.of(
                                new Request(4, "Issue4", "Model4", null, Status.NEW, null, null),
                                new Request(5, "Issue5", "Model5", null, Status.NEW, null, null),
                                new Request(6, "Issue6", "Model6", null, Status.NEW, null, null)
                        ),
                        true // Wszystkie requesty z NEW
                ),
                Arguments.of(
                        List.of(
                                new Request(7, "Issue7", "Model7", null, Status.ASSIGNED, null, null),
                                new Request(8, "Issue8", "Model8", null, Status.IN_PROGRESS, null, null)
                        ),
                        false // Brak requestów z NEW
                )
        );
    }
}
