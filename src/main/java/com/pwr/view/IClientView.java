package com.pwr.view;

import com.pwr.model.Request;

import java.util.List;

public interface IClientView {

	void displayNotification(String message);

	void displayRepairs(List<Request> requests);

	int getSelectedRepairId();

	boolean getClientChoice(String message);

	String enterReviewDescription();

	int enterReviewRate();

}