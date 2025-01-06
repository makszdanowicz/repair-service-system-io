package com.pwr.model;

import com.pwr.model.Request;

import java.util.List;

public interface RequestDAO {

	/**
	 * 
	 * @param request
	 */
	void addRequest(Request request);

	void updateRequest(int updatedRequestId, String status);

	/**
	 * 
	 * @param requestId
	 */
	Request getRequestById(int requestId);

	List<Request> getAllRequests();
	List<Integer> getRequestsIdCreatedByThisClient(int clientId);
	void deleteRequest(Request request);

}