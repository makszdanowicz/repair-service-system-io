package com.pwr.model;

import java.util.List;

public class Client {

	private int id;
	private String personalData;
	private String address;
	private String email;
	//private List<Request> requests;
	private List<Integer> requestsId;

	public Client(int id, String personalData, String address, String email, List<Integer> requestsId) {
		this.id = id;
		this.personalData = personalData;
		this.address = address;
		this.email = email;
		this.requestsId = requestsId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(String personalData) {
		this.personalData = personalData;
	}

	public String getAddress() {
		return this.address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getRequestsId() {
		return requestsId;
	}

	public void setRequestsId(List<Integer> requestsId) {
		this.requestsId = requestsId;
	}

	//	public List<Request> getRequests() {
//		return this.requests;
//	}
//
//	public void setRequests(List<Request> requests) {
//		this.requests = requests;
//	}

}