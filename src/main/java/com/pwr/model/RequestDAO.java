package com.pwr.model;

import com.pwr.model.Request;

import java.util.List;

public interface RequestDAO {

	/**
	 * 
	 * @param request
	 */
	void addRequest(Request request);

	/**
	 * 
	 * @param updatedRequest
	 */
	void updateRequest(Request updatedRequest);

	/**
	 * 
	 * @param requestId
	 */
	Request getRequestById(int requestId);

	List<Request> getAllRequests();

	/**
	 * 
	 * @param request
	 */
	void deleteRequest(Request request);

}