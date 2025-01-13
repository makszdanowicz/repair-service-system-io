package com.pwr.presenter;

import com.pwr.model.*;
import com.pwr.view.IClientView;

import java.util.ArrayList;
import java.util.List;

public class HistoryReviewFacade {

	private RequestDAO requestDAO;
	private ReviewDAO reviewDAO;
	IClientView clientHistoryView;

	public HistoryReviewFacade(RequestDAO requestDAO, ReviewDAO reviewDAO, IClientView clientHistoryView) {
		this.requestDAO = requestDAO;
		this.reviewDAO = reviewDAO;
		this.clientHistoryView = clientHistoryView;
	}

	public boolean generateRepairHistory() {
		List<Request> allRequests = requestDAO.getAllRequests();
		List<Request> completedRepairs = getCompletedRepairs(allRequests);
		if(completedRepairs.isEmpty()){
			clientHistoryView.displayNotification("There are no existing finished repairs");
			return false;
		}
		clientHistoryView.displayRepairs(completedRepairs);
		return true;
	}

	public void addReviewAboutRepair() {
		String message = "Would you like to add review about your finished repair?Type 'yes' or 'no'";
		boolean clientChoice = clientHistoryView.getClientChoice(message);
		if(!clientChoice){
			return;
		}
		clientHistoryView.displayNotification("Enter request id: ");
		int repairID = clientHistoryView.getSelectedRepairId();
		clientHistoryView.displayNotification("Enter yor rating(1 to 5): ");
		int rate = clientHistoryView.enterReviewRate();
		clientHistoryView.displayNotification("Share your opinion: ");
		String descriptionOfRepair = clientHistoryView.enterReviewDescription();
		Request request = requestDAO.getRequestById(repairID);
		Review review = new Review(rate,descriptionOfRepair,request);
		reviewDAO.addReview(review);

	}

	private List<Request> getCompletedRepairs(List<Request> requests){
		List<Request> completedRepairs = new ArrayList<>();
		for(Request request : requests){
			if(request.getStatus() == Status.COMPLETED && !reviewDAO.isReviewExist(request.getId())){
				completedRepairs.add(request);
			}
		}
		return completedRepairs;
	}

}