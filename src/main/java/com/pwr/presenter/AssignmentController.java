package com.pwr.presenter;

public class AssignmentController {

	private AssignmentFacade assignmentFacade;

	public AssignmentController(AssignmentFacade assignmentFacade) {
		this.assignmentFacade = assignmentFacade;
	}

	public void handleRequestAssignment() {
		boolean isNewRepairExists = assignmentFacade.sendNewRequests();
		if(!isNewRepairExists){
			return;
		}
		int idOfRequest = assignmentFacade.getAdminChoice("Wpisz id zgloszenia z dostepnych do przydzielenia: \n");
		assignmentFacade.assignTechnicianToRequest();
		int idOfTechnician = assignmentFacade.getAdminChoice("Wpisz id serwisanta, ktorego chcesz przydzielic do " +
															 "zgloszenia o id: " + idOfRequest);
		// dalej idzie dodawanie do DAO TehcnicianDAO(Request) i Request(Technician) i zmiana statusu i wyswietlanie wiadomosci
	}

}