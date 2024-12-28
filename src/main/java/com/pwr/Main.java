package com.pwr;

import com.pwr.database.DataBase;
import com.pwr.model.*;
import com.pwr.presenter.AssignmentController;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.view.*;

public class Main {
	public static void main(String[] args) {
		DataBase dataBase = new DataBase();

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
	}
}