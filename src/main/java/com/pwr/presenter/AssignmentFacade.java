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

	private List<Request> getNewRequest(List<Request> allRequests){
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
		List<Technician> availableTechnicians = getAvailableTechnicians(technicianDAO.getAllTechnicians());
		if(availableTechnicians.isEmpty()){
			SystemObserver systemObserver = new SystemObserver(technicianDAO.getAllTechnicians());
			int idOfAvailableTechnician = -1;
			while(idOfAvailableTechnician == -1){
				idOfAvailableTechnician = systemObserver.getIdOfAvailableTechnician();
				try {
					Thread.sleep(100); // czekamy 100ms zeby nie bylo nadmiarowosci
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Technician availableTechnician = technicianDAO.getTechnicianById(idOfAvailableTechnician);
			availableTechnicians.add(availableTechnician);
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

	private List<Request> getNewRequests(List<Request> allRequests) {
		// TODO - implement AssignmentFacade.getNewRequests
		throw new UnsupportedOperationException();
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