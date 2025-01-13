package com.pwr.view;

import com.pwr.model.Request;

import java.util.List;

public interface IClientView {

	/**
	 * 
	 * @param message
	 */
	void displayNotification(String message);

	/**
	 * 
	 * @param requests
	 */
	void displayRepairs(List<Request> requests);

	int getSelectedRepairId();

	public boolean getClientChoice(String message);

	String enterReviewDescription();

	int enterReviewRate();

}