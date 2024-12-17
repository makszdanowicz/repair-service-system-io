package com.pwr.presenter;

import com.pwr.presenter.HistoryReviewFacade;

public class RepairHistoryHandler {

	HistoryReviewFacade historyReviewFacade;

	public RepairHistoryHandler(HistoryReviewFacade historyReviewFacade) {
		this.historyReviewFacade = historyReviewFacade;
	}

	public void handleRepairHistory() {
		boolean isCompletedRepairsExists = historyReviewFacade.generateRepairHistory();
		if(!isCompletedRepairsExists){
			return;
		}
		historyReviewFacade.addReview();
	}

}