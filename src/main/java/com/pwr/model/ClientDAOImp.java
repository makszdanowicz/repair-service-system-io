package com.pwr.model;

import com.pwr.model.Client;
import com.pwr.model.ClientDAO;

import java.sql.Connection;
import java.util.List;

public class ClientDAOImp implements ClientDAO {

	private Connection connection;

	/**
	 * 
	 * @param connection
	 */
	public ClientDAOImp(Connection connection) {
		// TODO - implement ClientDAOImp.ClientDAOImp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param client
	 */
	public void addClient(Client client) {
		// TODO - implement ClientDAOImp.addClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param updatedClient
	 */
	public void updateClient(Client updatedClient) {
		// TODO - implement ClientDAOImp.updateClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param clientId
	 */
	public Client getClientById(int clientId) {
		// TODO - implement ClientDAOImp.getClientById
		throw new UnsupportedOperationException();
	}

	public List<Client> getAllClients() {
		// TODO - implement ClientDAOImp.getAllClients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param client
	 */
	public void deleteClient(Client client) {
		// TODO - implement ClientDAOImp.deleteClient
		throw new UnsupportedOperationException();
	}

}