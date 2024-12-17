package com.pwr.model;

import java.util.List;

public class Client {

	private int id;
	private String personalData;
	private String address;
	private String email;
	private List<Request> requests;

	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getPersonalData() {
		return this.personalData;
	}

	/**
	 * 
	 * @param personalData
	 */
	public void setPersonalData(String personalData) {
		this.personalData = personalData;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Request> getRequests() {
		return this.requests;
	}

	/**
	 * 
	 * @param requests
	 */
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}