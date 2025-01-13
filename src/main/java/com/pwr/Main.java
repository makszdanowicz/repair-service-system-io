package com.pwr;

import com.pwr.database.DataBase;
import com.pwr.database.LoginService;
import com.pwr.database.User;
import com.pwr.model.*;
import com.pwr.presenter.AssignmentController;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.presenter.HistoryReviewFacade;
import com.pwr.presenter.RepairHistoryHandler;
import com.pwr.view.*;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		DataBase dataBase = new DataBase();

		// Zalogowanie sie uzytkownika
		User user = LoginService.login(dataBase.getConnection());
		if(user!=null){
			System.out.println("Welcome, " + user.getUserName());
			switch (user.getRole()) {
				case "CLIENT" :
					new Thread(()-> handleClient(dataBase.getConnection())).start();
					break;
				case "TECHNICIAN" :
					new Thread(()-> handleTechnician(dataBase.getConnection())).start();
					break;
				case "ADMIN" :
					new Thread(()-> handleAdmin(dataBase.getConnection())).start();
					break;
			}
		}
	}

	private static void handleClient(Connection connection){
		// Implementacja operacji dla klienta
		boolean running = true;
		while (running){
			System.out.println("1. View history of repairs");
			System.out.println("2. Close program");
			System.out.println("Choose the option: ");
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			if(option == 1){
				// Realizacja przypadku uzycia 11
				ReviewDAO reviewDAO = new ReviewDAOImp(connection);
				RequestDAO requestDAO = new RequestDAOImp(connection);
				IClientView clientView = new ClientView();
				HistoryReviewFacade historyReviewFacade = new HistoryReviewFacade(requestDAO,reviewDAO,clientView);
				RepairHistoryHandler repairHistoryHandler = new RepairHistoryHandler(historyReviewFacade);
				repairHistoryHandler.handleRepairHistory();
			} else if (option == 2) {
				running = false;
				System.out.println("Exiting program...");
			}
		}
	}

	private static void handleTechnician(Connection connection){
		System.out.println("Technician view loaded...");
		// Implementacja operacji dla klienta
	}

	private static void handleAdmin(Connection connection){
		// Implementacja operacji dla administratora
		boolean running = true;
		while (running){
			System.out.println("1. Assign a request to a technician");
			System.out.println("2. Close program");
			System.out.println("Choose the option: ");
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			if(option == 1){
				// Realizacja przypadku uzycia 4
				RequestDAO requestDAO = new RequestDAOImp(connection);
				TechnicianDAO technicianDAO = new TechnicianDAOImp(connection);
				ClientDAO clientDAO = new ClientDAOImp(connection);
				IAdminView adminView = new AdminView();
				IClientView clientView = new ClientView();
				ITechnicianView technicianView = new TechnicianView();
				AssignmentFacade assignmentFacade = new AssignmentFacade(requestDAO,technicianDAO,clientDAO,adminView,clientView,
						technicianView);
				AssignmentController assignmentController = new AssignmentController(assignmentFacade);
				assignmentController.handleRequestAssignment();
			} else if(option == 2){
				running = false;
				System.out.println("Exiting program...");
			}
		}
	}
}