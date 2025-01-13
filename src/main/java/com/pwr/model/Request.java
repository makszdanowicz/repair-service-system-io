package com.pwr.model;

import java.util.Date;

public class Request {

	private int id;
	private String issueDescription;
	private String deviceModel;
	private Client client;
	private Status status;
	private Technician assignedTechnician;
	private Date date;
	public Request(int id, String issueDescription, String deviceModel, Client client, Status status, Technician assignedTechnician, Date date) {
		this.id = id;
		this.issueDescription = issueDescription;
		this.deviceModel = deviceModel;
		this.client = client;
		this.status = status;
		this.assignedTechnician = assignedTechnician;
		this.date = date;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssueDescription() {
		return this.issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Technician getAssignedTechnician() {
		return this.assignedTechnician;
	}

	public void setAssignedTechnician(Technician assignedTechnician) {
		this.assignedTechnician = assignedTechnician;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}