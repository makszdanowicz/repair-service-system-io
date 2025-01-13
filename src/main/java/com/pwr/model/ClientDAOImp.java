package com.pwr.model;

import com.pwr.model.Client;
import com.pwr.model.ClientDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientDAOImp implements ClientDAO {

	private Connection connection;

	public ClientDAOImp(Connection connection) {
		this.connection = connection;
	}

	public void addClient(Client client) {
		// TODO - implement ClientDAOImp.addClient
		throw new UnsupportedOperationException();
	}

	public void updateClient(Client updatedClient) {
		// TODO - implement ClientDAOImp.updateClient
		throw new UnsupportedOperationException();
	}

	public Client getClientById(int clientId) {
		Client client = null;
		String getClientQuery = "SELECT user_id, personal_data, address, email FROM clients WHERE user_id = ?";
		try{
			// Przygotowanie zapytania
			PreparedStatement preparedStatement = connection.prepareStatement(getClientQuery);
			preparedStatement.setInt(1,clientId);

			// Wykonanie zapytania i uzyskanie wynikow
			ResultSet resultSet = preparedStatement.executeQuery();

			// Sprawdzenie, czy istnieje wierz wynikowy
			if(resultSet.next()){
				// Pobranie danych z bazy
				int id = resultSet.getInt("user_id");
				String personalData = resultSet.getString("personal_data");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");

				RequestDAO requestDAO = new RequestDAOImp(connection);
				List<Integer> requestsId = requestDAO.getRequestsIdCreatedByThisClient(clientId);
				client = new Client(id,personalData,address,email,requestsId);
			}

		} catch (SQLException e) {
			System.out.println("Can't get Client from DataBase: " + e.getMessage());
		}
		return client;
	}

	public List<Client> getAllClients() {
		// TODO - implement ClientDAOImp.getAllClients
		throw new UnsupportedOperationException();
	}

	public void deleteClient(Client client) {
		// TODO - implement ClientDAOImp.deleteClient
		throw new UnsupportedOperationException();
	}


}