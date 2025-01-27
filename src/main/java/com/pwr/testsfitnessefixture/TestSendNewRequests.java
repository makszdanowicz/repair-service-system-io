package com.pwr.testsfitnessefixture;

import com.pwr.model.*;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;
import fit.ColumnFixture;
import mockit.Mocked;
import mockit.Expectations;

import java.util.ArrayList;
import java.util.List;

public class TestSendNewRequests extends ColumnFixture {

    public Integer[] requestsId;
    public String[] statuses;

    @Mocked
    private ClientDAO clientDAO;

    @Mocked
    private RequestDAO requestDAO;

    @Mocked
    private TechnicianDAO technicianDAO;

    @Mocked
    private IAdminView adminView;

    @Mocked
    private IClientView clientView;

    @Mocked
    private ITechnicianView technicianView;


    public boolean sendNewRequests(){
        AssignmentFacade assignmentFacade = new AssignmentFacade(requestDAO, technicianDAO, clientDAO, adminView, clientView, technicianView);

        new Expectations(){{
            List<Request> allRequests = new ArrayList<>();
                for(int i = 0; i < requestsId.length; i++){
                    Integer requestId = requestsId[i];
                    String statusDescription = statuses[i];
                    Status status = Status.convertDescription(statusDescription);
                    Request request = new Request(requestId, null, null, null, status, null, null);
                    allRequests.add(request);
                }
                requestDAO.getAllRequests(); result = allRequests;
        }};
        return assignmentFacade.sendNewRequests();
    }
}