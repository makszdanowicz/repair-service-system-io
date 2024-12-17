package com.pwr.presenter;

import com.pwr.model.*;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;

import java.util.List;

public class AssignmentFacade {

	private RequestDAO requestDAO;
	private TechnicianDAO technicianDAO;
	private ClientDAO clientDAO;
	private IAdminView adminView;
	private IClientView clientView;
	private ITechnicianView technicianView;

	/**
	 * 
	 * @param requestDAO
	 * @param technicianDAO
	 * @param clientDAO
	 * @param adminView
	 * @param clientView
	 * @param technicianView
	 */
	public AssignmentFacade(RequestDAO requestDAO, TechnicianDAO technicianDAO, ClientDAO clientDAO, IAdminView adminView, IClientView clientView, ITechnicianView technicianView) {
		// TODO - implement AssignmentFacade.AssignmentFacade
		throw new UnsupportedOperationException();
	}

	public void assignTechnicianToRequest() {
		// TODO - implement AssignmentFacade.assignTechnicianToRequest
		throw new UnsupportedOperationException();
	}

	private List<Technician> getAvailableTechnicians() {
		// TODO - implement AssignmentFacade.getAvailableTechnicians
		throw new UnsupportedOperationException();
	}

	private List<Request> getNewRequests() {
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