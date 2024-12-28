package com.pwr.presenter;

import com.pwr.model.*;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;

import java.util.ArrayList;
import java.util.List;

public class AssignmentFacade {

	private RequestDAO requestDAO;
	private TechnicianDAO technicianDAO;
	private ClientDAO clientDAO;
	private IAdminView adminView;
	private IClientView clientView;
	private ITechnicianView technicianView;

	public AssignmentFacade(RequestDAO requestDAO, TechnicianDAO technicianDAO, ClientDAO clientDAO, IAdminView adminView, IClientView clientView, ITechnicianView technicianView) {
		this.requestDAO = requestDAO;
		this.technicianDAO = technicianDAO;
		this.clientDAO = clientDAO;
		this.adminView = adminView;
		this.clientView = clientView;
		this.technicianView = technicianView;
	}

	public boolean sendNewRequests(){
		List<Request> newRequests = getNewRequests(requestDAO.getAllRequests());
		adminView.displayNewRequests(newRequests);
		return !newRequests.isEmpty();
	}

	public List<Request> getNewRequests(List<Request> allRequests){
		List<Request> newRequests = new ArrayList<>();
		for(Request request : allRequests){
			if(request.getStatus() == Status.NEW){
				newRequests.add(request);
			}
		}
		return newRequests;
	}

	public int getAdminChoice(String message){
		return adminView.getSelectedId(message);
	}

	public void assignTechnicianToRequest() {
		List<Technician> technicians = technicianDAO.getAllTechnicians();
		List<Technician> availableTechnicians = getAvailableTechnicians(technicians);
		if(availableTechnicians.isEmpty()){
			SystemObserver systemObserver = new SystemObserver(technicians);
			int idOfAvailableTechnician = systemObserver.getIdOfAvailableTechnician();
			Technician technician = technicianDAO.getTechnicianById(idOfAvailableTechnician);
			availableTechnicians.add(technician);
		}
		adminView.displayAvailableTechnicians(availableTechnicians);
	}

	private List<Technician> getAvailableTechnicians(List<Technician> allTechnicians) {
		List<Technician> availableTechnicians = new ArrayList<>();
		for(Technician technician : allTechnicians){
			if(technician.getAvailability()){
				availableTechnicians.add(technician);
			}
		}
		return availableTechnicians;
	}


	/**
	 *
	 * @param requestId
	 * @param newStatus
	 */
	public void updateStatusOfRequest(int requestId, Status newStatus) {
		// TODO - implement AssignmentFacade.updateStatusOfRequest
		throw new UnsupportedOperationException();
	}

}