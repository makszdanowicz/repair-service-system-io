package com.pwr.model;

import com.pwr.model.Request;
import com.pwr.presenter.IObserver;

public class Technician {

	private int id;
	private String personalData;
	private boolean availability;
	private int requestId;
	private IObserver systemObserver;

	public Technician(int id, String personalData, boolean availability, int requestId) {
		this.id = id;
		this.personalData = personalData;
		this.availability = availability;
		this.requestId = requestId;
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

	public void setAvailability(boolean availability) {
		this.availability = availability;
		if(availability){
			if(systemObserver != null){
				notifyObserver();
			}
		}
	}

	public boolean isAvailability() {
		return availability;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public void notifyObserver() {
		systemObserver.update(this.id);
	}

	public void setObserver(IObserver observer) {
		this.systemObserver = observer;
	}

	public void deattachObserver() {
		this.systemObserver = null;
	}

}