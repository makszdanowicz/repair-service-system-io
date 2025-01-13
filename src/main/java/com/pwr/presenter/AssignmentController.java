package com.pwr.presenter;

import com.pwr.model.Technician;

public class AssignmentController {

	private AssignmentFacade assignmentFacade;

	public AssignmentController(AssignmentFacade assignmentFacade) {
		this.assignmentFacade = assignmentFacade;
	}

	public void handleRequestAssignment() {
		boolean isNewRequestsExists = assignmentFacade.sendNewRequests();
		if (!isNewRequestsExists){
			return;
		}
		assignmentFacade.assignTechnicianToRequest();
	}


}