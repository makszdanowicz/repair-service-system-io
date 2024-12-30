package com.pwr.view;

import com.pwr.model.Request;

import java.util.List;
import java.util.Scanner;

public class ClientView implements IClientView {

	/**
	 *
	 * @param message
	 */
	public void displayNotification(String message) {
		// TODO - implement ClientView.displayNotification
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param requests
	 */
	@Override
	public void displayRepairs(List<Request> requests) {

	}

	public int getSelectedRepairId() {
		// TODO - implement ClientView.getSelectedRepairId
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getClientChoice(String message) {
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		String clientChoice = scanner.next();
		if(clientChoice.equals("tak")){
			return true;
		} else if(clientChoice.equals("nie")){
			return false;
		}
		return false;
	}


	public String enterReviewDescription() {
		// TODO - implement ClientView.enterReviewDescription
		throw new UnsupportedOperationException();
	}

	public int enterReviewRate() {
		// TODO - implement ClientView.enterReviewRate
		throw new UnsupportedOperationException();
	}

}