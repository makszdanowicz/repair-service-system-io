package com.pwr.model;

import com.pwr.model.Technician;
import com.pwr.model.TechnicianDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TechnicianDAOImp implements TechnicianDAO {

	private Connection connection;

	public TechnicianDAOImp(Connection connection) {
		this.connection = connection;
	}

	public void addTechnician(Technician technician) {
		// TODO - implement TechnicianDAOImp.addTechnician
		throw new UnsupportedOperationException();
	}

	public void updateTechnician(Technician updatedTechnician) {
		// TODO - implement TechnicianDAOImp.updateTechnician
		throw new UnsupportedOperationException();
	}


	public Technician getTechnicianById(int technicianId) {
		Technician technician = null;
		String getTechnicianQuery = "SELECT user_id, personal_data, availability, request_id WHERE user_id = ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(getTechnicianQuery);
			preparedStatement.setInt(1,technicianId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				String personalData = resultSet.getString("personal_data");
				boolean availability = resultSet.getBoolean("availability");
				int requestId = resultSet.getInt("request_id");

				technician = new Technician(technicianId, personalData, availability, requestId);
			}
		} catch (SQLException e) {
			System.out.println("Can't get a Technician from DataBase: " + e.getMessage());
		}
		return technician;
	}

	@Override
	public Technician getTechnicianByRequestId(int requestId) {
		Technician technician = null;
		String getTechnicianQuery = "SELECT user_id, personal_data, availability, request_id WHERE request_id = ?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(getTechnicianQuery);
			preparedStatement.setInt(1,requestId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				int id = resultSet.getInt("user_id");
				String personalData = resultSet.getString("personal_data");
				boolean availability = resultSet.getBoolean("availability");

				technician = new Technician(id, personalData, availability, requestId);
			}
		} catch (SQLException e) {
			System.out.println("Can't get a Technician from DataBase: " + e.getMessage());
		}
		return technician;
	}

	public List<Technician> getAllTechnicians() {
		// TODO - implement TechnicianDAOImp.getAllTechnicians
		throw new UnsupportedOperationException();
	}

	public void deleteTechnician(Technician technician) {
		// TODO - implement TechnicianDAOImp.deleteTechnician
		throw new UnsupportedOperationException();
	}

}