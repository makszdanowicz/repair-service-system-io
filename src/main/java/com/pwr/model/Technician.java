package com.pwr.model;

import com.pwr.model.Request;
import com.pwr.presenter.IObserver;

public class Technician {

	private int id;
	private String personalData;
	private boolean availability;
	private Request request;
	private IObserver systemObserver;

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

	public boolean getAvailability() {
		return this.availability;
	}

	/**
	 * 
	 * @param availability
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Request getRequest() {
		return this.request;
	}

	/**
	 * 
	 * @param request
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	public void notifyObserver() {
		// TODO - implement Technician.notifyObserver
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param observer
	 */
	public void setObserver(IObserver observer) {
		// TODO - implement Technician.setObserver
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param observer
	 */
	public void deattachObserver(IObserver observer) {
		// TODO - implement Technician.deattachObserver
		throw new UnsupportedOperationException();
	}

}