package com.pwr.presenter;

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
		int idOfRequest = assignmentFacade.getAdminChoice("Wpisz id zgloszenia z dostepnych do przydzielenia: ");
		assignmentFacade.assignTechnicianToRequest();
		int idOfTechnician = assignmentFacade.getAdminChoice("Wpisz id serwisanta, ktorego chcesz przydzielic do zgloszenia: ");
	}


}