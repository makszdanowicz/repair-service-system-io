package com.pwr.model;

import com.pwr.model.Request;
import com.pwr.presenter.IObserver;

public class Technician {

	private int id;
	private String personalData;
	private boolean availability;
	private Request request;
	private IObserver systemObserver;

	public Technician(int id, String personalData, boolean availability, Request request){
		this.id = id;
		this.personalData = personalData;
		this.availability = availability;
		this.request = request;
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

	public boolean getAvailability() {
		return this.availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
		if(availability){
			notifyObserver();
		}
	}

	public Request getRequest() {
		return this.request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void notifyObserver() {
		systemObserver.update(this.id);
	}


	public void setObserver(IObserver observer) {
		this.systemObserver = observer;
	}


	public void deattachObserver(IObserver observer) {

	}

}