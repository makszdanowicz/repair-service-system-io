package com.pwr;

import com.pwr.database.DataBase;
import com.pwr.database.User;
import com.pwr.model.*;
import com.pwr.presenter.AssignmentController;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.presenter.HistoryReviewFacade;
import com.pwr.presenter.RepairHistoryHandler;
import com.pwr.view.*;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		DataBase dataBase = new DataBase();

		//User login
		User user = LoginService.login(dataBase.getConnection());
		if(user!=null){
			System.out.println("Welcome, " + user.getUserName());
			switch (user.getRole()) {
				case "CLIENT" :
					System.out.println("Client");
					break;
				case "TECHNICIAN" :
					System.out.println("Technician");
					break;
				case "ADMIN" :
					System.out.println("Admin");
					break;
			}
		}

		// Realization of UseCase 4
		RequestDAO requestDAO = new RequestDAOImp(dataBase.getConnection());
		TechnicianDAO technicianDAO = new TechnicianDAOImp(dataBase.getConnection());
		ClientDAO clientDAO = new ClientDAOImp(dataBase.getConnection());
		IAdminView adminView = new AdminView();
		IClientView clientView = new ClientView();
		ITechnicianView technicianView = new TechnicianView();
		AssignmentFacade assignmentFacade = new AssignmentFacade(requestDAO,technicianDAO,clientDAO,adminView,clientView,
				technicianView);
		AssignmentController assignmentController = new AssignmentController(assignmentFacade);
		assignmentController.handleRequestAssignment();

		// Realization of UseCase 11
		ReviewDAO reviewDAO = new ReviewDAOImp(dataBase.getConnection());
		HistoryReviewFacade historyReviewFacade = new HistoryReviewFacade(requestDAO,reviewDAO,clientView);
		RepairHistoryHandler repairHistoryHandler = new RepairHistoryHandler(historyReviewFacade);
		repairHistoryHandler.handleRepairHistory();
	}
}