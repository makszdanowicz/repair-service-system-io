package com.pwr.view;

import com.pwr.model.Request;
import com.pwr.model.Technician;

import java.util.List;

public interface IAdminView {

	/**
	 * 
	 * @param requests
	 */
	void displayNewRequests(List<Request> requests);

	/**
	 * 
	 * @param technicians
	 */
	void displayAvailableTechnicians(List<Technician> technicians);

	void requestAssignmentInput();

	int getSelectedId(String message);

	/**
	 * 
	 * @param technicianId
	 */
	void notifyTechnicianAvailable(int technicianId);

}