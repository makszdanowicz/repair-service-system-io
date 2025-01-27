package com.pwr.testsfitnessefixture;

import com.pwr.model.Request;
import com.pwr.model.Status;
import com.pwr.presenter.AssignmentFacade;
import fit.ColumnFixture;

import java.util.ArrayList;
import java.util.List;

public class TestGetNewRequests extends ColumnFixture {
    public Integer[] requestsId;
    public String[] statuses;

    public boolean getNewRequests(){
        AssignmentFacade assignmentFacade = new AssignmentFacade(null, null, null, null, null, null);
        List<Request> allRequests = new ArrayList<>();
        for(int i = 0; i < requestsId.length; i++){
            Integer requestId = requestsId[i];
            String statusDescription = statuses[i];
            Status status = Status.convertDescription(statusDescription);
            Request request = new Request(requestId, null, null, null, status, null, null);
            allRequests.add(request);
        }

        return !assignmentFacade.getNewRequests(allRequests).isEmpty();
    }
}
