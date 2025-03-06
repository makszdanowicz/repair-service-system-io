package com.pwr;

import com.pwr.model.*;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.presenter.SystemObserver;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;
import mockit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(IgnoreNullPointerExceptionExtension.class)
public class AssignmentFacadeTest {

    // @Mocked - mockowane wszystkie instancji tej klasy
    // @Injectable - mockowana tylko instancja nad ktorej jest adnotacja

    @Mocked
    private ClientDAO clientDAO;

    @Mocked
    private RequestDAO requestDAO; // Pole, ktore bedzie mockowane

    @Mocked
    private TechnicianDAO technicianDAO;

    @Mocked
    private IAdminView adminView;

    @Mocked
    private IClientView clientView;

    @Mocked
    private ITechnicianView technicianView;

    private AssignmentFacade assignmentFacade;

    @BeforeEach
    void setUp() {
        assignmentFacade = new AssignmentFacade(requestDAO, technicianDAO, clientDAO, adminView, clientView, technicianView);
    }

    @Test
    void testGetNewRequests() {
        Request newRequest = new Request(1, "Issue1", "Model1", null, Status.NEW, null, null);
        Request oldRequest = new Request(2, "Issue2", "Model2", null, Status.ASSIGNED, null, null);

        List<Request> result = assignmentFacade.getNewRequests(Arrays.asList(newRequest, oldRequest));

        assertEquals(1, result.size(), "Expected only one new request.");
        assertEquals(1, result.get(0).getId(), "Expected the new request with id 1.");
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

    @Test
    void testAssignTechnicianToRequest_NoAvailableTechnicians(@Mocked SystemObserver systemObserver){
        Technician busyTechnician = new Technician(1, "Personal Data 1",false, 0);
        Request request = new Request(1, "Issue1", "Model1", null, Status.NEW, null, null);

        new Expectations(){{
            technicianDAO.getAllTechnicians(); result = Collections.singletonList(busyTechnician);

            systemObserver.getIdOfAvailableTechnician(); result = 1;

            technicianDAO.getTechnicianById(1); result = busyTechnician;

            adminView.getSelectedId("Enter the id of request: "); result = 1;

            adminView.getSelectedId("Enter the id of technician: "); result = 1;

        }};

        assignmentFacade.assignTechnicianToRequest();

        new Verifications() {{
            adminView.displayAvailableTechnicians(Collections.singletonList(busyTechnician));
            adminView.displayNotification("Request with id 1 successfully assigned to the technician 1");
        }};
    }

    @Test
    void testAssignTechnicianToRequest_AvailableTechnician(){
        Technician freeTechnician = new Technician(1, "Personal Data 1",true, 0);
        Request request = new Request(1, "Issue1", "Model1", null, Status.NEW, null, null);

        new Expectations(){{
           technicianDAO.getAllTechnicians(); result = Collections.singletonList(freeTechnician);

           adminView.getSelectedId("Enter the id of request: "); result = 1;

           adminView.getSelectedId("Enter the id of technician: "); result = 1;

            requestDAO.updateRequest(1, "PENDING");
            technicianDAO.updateTechnician(1, 1);
            requestDAO.updateRequest(1, "ASSIGNED");
        }};

        assignmentFacade.assignTechnicianToRequest();

        new Verifications() {{
            adminView.displayAvailableTechnicians(Collections.singletonList(freeTechnician));
            adminView.displayNotification("Request with id 1 successfully assigned to the technician 1");
        }};
    }

    @Test
    void testAssignTechnicianToRequest_NoAvailableTechnicianAndAvailable(){
        Technician busyTechnician = new Technician(1, "Personal Data 1",false, 0);
        Technician freeTechnician = new Technician(2, "Personal Data 2",true, 0);
        Request request = new Request(1, "Issue1", "Model1", null, Status.NEW, null, null);

        new Expectations(){{
            technicianDAO.getAllTechnicians(); result = List.of(busyTechnician, freeTechnician);

            adminView.getSelectedId("Enter the id of request: "); result = 1;

            adminView.getSelectedId("Enter the id of technician: "); result = 1;

        }};

        assignmentFacade.assignTechnicianToRequest();

        new Verifications() {{
            adminView.displayAvailableTechnicians(Collections.singletonList(freeTechnician));
            adminView.displayNotification("Request with id 1 successfully assigned to the technician 1");
        }};
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
