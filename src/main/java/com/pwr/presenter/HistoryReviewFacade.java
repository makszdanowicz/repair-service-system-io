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
		List<Request> completedRequests = getCompletedRepairs(requestDAO.getAllRequests());
		if(completedRequests.isEmpty()){
			clientHistoryView.displayNotification("Brak ukonczonych napraw");
			return false;
		}
		return true;
	}

	private List<Request> getCompletedRepairs(List<Request> allRequests){
		List<Request> completedRequests = new ArrayList<>();
		for(Request request : allRequests){
			if(request.getStatus() == Status.COMPLETED){
				completedRequests.add(request);
			}
		}
		clientHistoryView.displayRepairs(completedRequests);
		return completedRequests;
	}


	public void addReview() {
		boolean clientChoice = clientHistoryView.getClientChoice("Czy chcesz dodac opinie, wpisz 'tak' lub 'nie'");
		if(!clientChoice){
			return;
		}
		clientHistoryView.displayNotification("Wpisz id zgloszenia, do ktorego chcesz dodac opinie: ");
		int repairID = clientHistoryView.getSelectedRepairId();
		clientHistoryView.displayNotification("Jak oceniasz wykonana naprawe(wpisz liczbe od 1 do 5): ");
		int rate = clientHistoryView.enterReviewRate();
		clientHistoryView.displayNotification("Podziel sie opinia wykonanej naprawy(krotki tekst): ");
		String description = clientHistoryView.enterReviewDescription();
		/*
		TO DO
		Dodajemy do ReviewDAO nasza opnie
		 */
	}


}