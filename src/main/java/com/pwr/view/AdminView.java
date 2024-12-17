package com.pwr.view;

import com.pwr.model.Request;
import com.pwr.model.Technician;

import java.util.List;
import java.util.Scanner;

public class AdminView implements IAdminView {

	/**
	 *
	 * @param requests
	 */
	public void displayNewRequests(List<Request> requests) {
		// TODO - implement AdminView.displayNewRequests
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param technicians
	 */
	public void displayAvailableTechnicians(List<Technician> technicians) {
		// TODO - implement AdminView.displayAvailableTechnicians
		throw new UnsupportedOperationException();
	}

	public void requestAssignmentInput() {
		// TODO - implement AdminView.requestAssignmentInput
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSelectedId(String message) {
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	/**
	 *
	 * @param technicianId
	 */
	public void notifyTechnicianAvailable(int technicianId) {
		// TODO - implement AdminView.notifyTechnicianAvailable
		throw new UnsupportedOperationException();
	}

}