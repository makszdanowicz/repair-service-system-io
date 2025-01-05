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
			clientHistoryView.displayNotification("Brak ukonczonych napraw");
			return false;
		}
		return true;
	}

	public void addReviewAboutRepair() {
		boolean clientChoice = clientHistoryView.getClientChoice("Czy chcesz dodac opinie, wpisz 'tak' lub 'nie'");
		if(!clientChoice){
			return;
		}
		clientHistoryView.displayNotification("Wpisz id zgloszenia, do ktorego chcesz dodac opnie: ");
		int repairID = clientHistoryView.getSelectedRepairId();
		clientHistoryView.displayNotification("Jak oceniasz wykonana naprawe(wpisz liczbe od 1 do 5): ");
		int rate = clientHistoryView.enterReviewRate();
		clientHistoryView.displayNotification("Podziel sie opinia wykonanej naprawy(krotki tekst): ");
		String descriptionOfRepair = clientHistoryView.enterReviewDescription();
		Request request = requestDAO.getRequestById(repairID);
		Review review = new Review(rate,descriptionOfRepair,request);
		reviewDAO.addReview(review);

	}

	private List<Request> getCompletedRepairs(List<Request> requests){
		List<Request> completedRepairs = new ArrayList<>();
		for(Request request : requests){
			if(request.getStatus() == Status.COMPLETED){
				completedRepairs.add(request);
			}
		}
		clientHistoryView.displayRepairs(completedRepairs);
		return completedRepairs;
	}

}