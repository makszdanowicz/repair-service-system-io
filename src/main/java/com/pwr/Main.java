package com.pwr;

import com.pwr.model.*;
import com.pwr.presenter.AssignmentController;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.presenter.HistoryReviewFacade;
import com.pwr.presenter.RepairHistoryHandler;
import com.pwr.view.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		// Tutaj bedzie podlaczanie do bazy danych
		String url = "jdbc:mysql://localhost:3306/nazwa_bazy_danych";
		String user = "nazwa_uzytkownika";
		String password = "haslo";
		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			// Wykonanie 4 przypadku uzycia
			RequestDAO requestDAO = new RequestDAOImp(connection);
			TechnicianDAO technicianDAO = new TechnicianDAOImp(connection);
			ClientDAO clientDAO = new ClientDAOImp(connection);
			IAdminView adminView = new AdminView();
			IClientView clientView = new ClientView();
			ITechnicianView technicianView = new TechnicianView();
			AssignmentFacade assignmentFacade = new AssignmentFacade(requestDAO,technicianDAO,clientDAO,adminView,
																	 clientView,technicianView);
			AssignmentController assignmentController = new AssignmentController(assignmentFacade);
			assignmentController.handleRequestAssignment();

			// Wykonanie 11 przypadku uzycia
			ReviewDAO reviewDAO = new ReviewDAOImp(connection);
			HistoryReviewFacade historyReviewFacade = new HistoryReviewFacade(requestDAO,reviewDAO,clientView);
			RepairHistoryHandler repairHistoryHandler = new RepairHistoryHandler(historyReviewFacade);
			repairHistoryHandler.handleRepairHistory();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}