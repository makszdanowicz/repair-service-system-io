package com.pwr.testsfitnessefixture;

import com.pwr.model.*;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.view.AdminView;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;
import fit.ColumnFixture;
import org.mockito.Mockito;
import mockit.Expectations;

import java.util.ArrayList;
import java.util.List;

public class TestSendNewRequests extends ColumnFixture {

    public Integer[] requestsId;
    public String[] statuses;

    private RequestDAO requestDAO = Mockito.mock(RequestDAO.class);


    public boolean sendNewRequests(){
        MockitoAnnotations.openMocks(this);
        AdminView adminView = new AdminView();
        AssignmentFacade assignmentFacade = new AssignmentFacade(requestDAO,null, null, adminView, null, null);

        List<Request> allRequests = new ArrayList<>();
        for(int i = 0; i < requestsId.length; i++){
            Integer requestId = requestsId[i];
            String statusDescription = statuses[i];
            Status status = Status.convertDescription(statusDescription);
            Request request = new Request(requestId, null, null, null, status, null, null);
            allRequests.add(request);
        }

        Mockito.when(requestDAO.getAllRequests()).thenReturn(allRequests);
        return assignmentFacade.sendNewRequests();
    }
}