package com.pwr.model;

import com.pwr.model.Request;
import com.pwr.model.RequestDAO;

import java.sql.Connection;
import java.util.List;

public class RequestDAOImp implements RequestDAO {

	private Connection connection;

	/**
	 * 
	 * @param connection
	 */
	public RequestDAOImp(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 
	 * @param request
	 */
	public void addRequest(Request request) {
		// TODO - implement RequestDAOImp.addRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param updatedRequest
	 */
	public void updateRequest(Request updatedRequest) {
		// TODO - implement RequestDAOImp.updateRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestId
	 */
	public Request getRequestById(int requestId) {
		// TODO - implement RequestDAOImp.getRequestById
		throw new UnsupportedOperationException();
	}

	public List<Request> getAllRequests() {
		// TODO - implement RequestDAOImp.getAllRequests
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param request
	 */
	public void deleteRequest(Request request) {
		// TODO - implement RequestDAOImp.deleteRequest
		throw new UnsupportedOperationException();
	}

}