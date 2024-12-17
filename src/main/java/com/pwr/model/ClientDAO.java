package com.pwr.model;

import com.pwr.model.Client;

import java.util.List;

public interface ClientDAO {

	/**
	 * 
	 * @param client
	 */
	void addClient(Client client);

	/**
	 * 
	 * @param updatedClient
	 */
	void updateClient(Client updatedClient);

	/**
	 * 
	 * @param clientId
	 */
	Client getClientById(int clientId);

	List<Client> getAllClients();

	/**
	 * 
	 * @param client
	 */
	void deleteClient(Client client);

}