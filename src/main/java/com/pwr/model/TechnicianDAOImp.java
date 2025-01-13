package com.pwr.model;

import com.pwr.model.Technician;
import com.pwr.model.TechnicianDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public void updateTechnician(int idOfUpdatedTechnician, int idOfRequest) {
		String updateTechnicianQuery = "UPDATE technicians SET availability = ?, request_id = ? WHERE user_id = ?";
		// Przygotowanie zapytania
		try(PreparedStatement preparedStatement = connection.prepareStatement(updateTechnicianQuery)){
			preparedStatement.setBoolean(1,false);
			preparedStatement.setInt(2, idOfRequest);
			preparedStatement.setInt(3,idOfUpdatedTechnician);

			// Wykonanie zapytania
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Can't update a Technician with id(" + idOfUpdatedTechnician + "): " + e.getMessage());
		}
	}

	@Override
	public Technician getTechnicianById(int technicianId) {
		String getTechnicianQuery = "SELECT user_id, personal_data, availability, request_id FROM technicians WHERE user_id = ?";
		return fetchTechnician(getTechnicianQuery,technicianId);
	}

	@Override
	public Technician getTechnicianByRequestId(int requestId) {
		String getTechnicianQuery = "SELECT user_id, personal_data, availability, request_id FROM technicians WHERE request_id = ?";
		return fetchTechnician(getTechnicianQuery, requestId);
	}

	private Technician fetchTechnician(String query, int parameter){
		// Przygotowanie zapytania
		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setInt(1, parameter);
			// Wykonanie zapytania
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				// Jezeli istnieje wierz, utworzenie obiektu Technician
				if(resultSet.next()){
					return mapToTechnician(resultSet);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error fetching technician from db with parameter: " + parameter + " "+ e.getMessage());
		}
		return null;
	}

	private Technician mapToTechnician(ResultSet resultSet){
		try {
			int id = resultSet.getInt("user_id");
			String personalData = resultSet.getString("personal_data");
			boolean availability = resultSet.getBoolean("availability");
			int requestId = resultSet.getInt("request_id");
			return new Technician(id, personalData, availability, requestId);
		} catch (SQLException e) {
			System.out.println("Error with mapping resulSet to Technician: " + e.getMessage());
		}
		return null;
	}

	public List<Technician> getAllTechnicians() {
		List<Technician> allTechnicians = new ArrayList<>();
		String getAllTechniciansQuery = "SELECT * FROM technicians";
		try(PreparedStatement preparedStatement = connection.prepareStatement(getAllTechniciansQuery)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Technician technician = mapToTechnician(resultSet);
					allTechnicians.add(technician);
				}
			}
		} catch (SQLException e) {
			System.out.println("Can't get all Technicians from DataBase: " + e.getMessage());
		}
		return allTechnicians;
	}

	public void deleteTechnician(Technician technician) {
		// TODO - implement TechnicianDAOImp.deleteTechnician
		throw new UnsupportedOperationException();
	}

}