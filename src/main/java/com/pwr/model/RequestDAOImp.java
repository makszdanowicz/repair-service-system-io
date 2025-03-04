package com.pwr.model;

import com.pwr.model.Request;
import com.pwr.model.RequestDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestDAOImp implements RequestDAO {

	private Connection connection;

	public RequestDAOImp(Connection connection) {
		this.connection = connection;
	}

	public void addRequest(Request request) {
		// TODO - implement RequestDAOImp.addRequest
		throw new UnsupportedOperationException();
	}

	public void updateRequest(int updatedRequestId, String status) {
		// Przygotowanie zapytania
		String updateRequestQuery = "UPDATE requests SET status = ? WHERE request_id = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(updateRequestQuery)){
			preparedStatement.setString(1,status);
			preparedStatement.setInt(2,updatedRequestId);

			// Wykonanie zapytania
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Can't update a Request(ID:" + updatedRequestId + "): " + e.getMessage());
		}
	}

	public Request getRequestById(int requestId) {
		// Przygotowanie zapytania
		String getRequestQuery = "SELECT request_id, issue_description, device_model, client_id, status, date FROM requests WHERE request_id = ?";
		try(PreparedStatement preparedStatement = connection.prepareStatement(getRequestQuery)){
			preparedStatement.setInt(1,requestId);
			// Wykonanie zapytania i uzyskanie wynikow
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				// Sprawdzenie, czy istnieje wierz wynikowy
				if(resultSet.next()){
					return mapToRequest(resultSet);
				}
			}
		} catch (SQLException e) {
			System.out.println("Can't get Request from DataBase: " + e.getMessage());
		}
		return null;
	}

	public List<Request> getAllRequests() {
		List<Request> requests = new ArrayList<>();
		// Przygotowanie zapytania
		String getAllRequestsQuery = "SELECT * FROM requests;";
		try(PreparedStatement preparedStatement = connection.prepareStatement(getAllRequestsQuery)) {
			// Wykonanie zapytania
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Iteracja przez wyniki i tworzenie obiektow Request
				while (resultSet.next()) {
					Request request = mapToRequest(resultSet);
					requests.add(request);
				}
			}
		} catch (SQLException e) {
			System.out.println("Can't get all Requests from DataBase: " + e.getMessage());
		}
		return requests;
	}

	private Request mapToRequest(ResultSet resultSet){
		try {
			// Pobranie danych z bazy
			int id = resultSet.getInt("request_id");
			String issueDescription = resultSet.getString("issue_description");
			String deviceModel = resultSet.getString("device_model");
			int clientId = resultSet.getInt("client_id");
			Date date = resultSet.getTimestamp("date");

			// Pobranie obiektu Client z innego DAO
			ClientDAOImp clientDAOImp = new ClientDAOImp(connection);
			Client client = clientDAOImp.getClientById(clientId);

			// Konwersja statusu na enum
			String statusString = resultSet.getString("status");
			Status status = Status.valueOf(statusString);

			// Pobranie obiektu Technician z innego DAO
			TechnicianDAOImp technicianDAOImp = new TechnicianDAOImp(connection);
			Technician assignedTechnician = technicianDAOImp.getTechnicianByRequestId(id);

			return new Request(id,issueDescription,deviceModel,client,status,assignedTechnician,date);

		} catch (SQLException e) {
			System.out.println("Can't map result set to Request: " + e.getMessage());
		}
		return null;
	}

	public List<Integer> getRequestsIdCreatedByThisClient(int clientId){
		List<Integer> requestsIds = new ArrayList<>();
		String getAllRequestsIdFromThisClientQuery = "SELECT request_id FROM requests WHERE client_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(getAllRequestsIdFromThisClientQuery)){
			preparedStatement.setInt(1,clientId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int requestId = resultSet.getInt("request_id");
				requestsIds.add(requestId);
			}
		} catch (SQLException e) {
			System.out.println("Can't get all Requests created by this Client from DataBase: " + e.getMessage());
		}
		return requestsIds;
	}


	public void deleteRequest(Request request) {
		// TODO - implement RequestDAOImp.deleteRequest
		throw new UnsupportedOperationException();
	}

}