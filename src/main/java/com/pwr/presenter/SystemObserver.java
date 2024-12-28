package com.pwr.presenter;

import com.pwr.model.Technician;
import com.pwr.presenter.IObserver;

import java.util.List;

public class SystemObserver implements IObserver {

	private List<Technician> monitoredTechnicians;
	private int idOfAvailableTechnician;

	public SystemObserver(List<Technician> monitoredTechnicians) {
		this.monitoredTechnicians = monitoredTechnicians;
		for(Technician technician : monitoredTechnicians){
			technician.setObserver(this);
		}
	}

	public void update(int availableTechnicianId) {
		System.out.println("Serwisant z id: " + availableTechnicianId + " jest teraz wolny");
		this.idOfAvailableTechnician = availableTechnicianId;
		for(Technician technician : monitoredTechnicians){
			technician.deattachObserver();
		}
	}

	public int getIdOfAvailableTechnician() {
		return idOfAvailableTechnician;
	}
}