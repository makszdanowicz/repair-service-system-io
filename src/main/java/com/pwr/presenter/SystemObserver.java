package com.pwr.presenter;

import com.pwr.model.Technician;
import com.pwr.presenter.IObserver;

import java.util.List;

public class SystemObserver implements IObserver {

	private List<Technician> monitoredTechnicians;
	private int idOfAvailableTechnician;

	public SystemObserver(List<Technician> monitoredTechnicians) {
		this.monitoredTechnicians = monitoredTechnicians;
		this.idOfAvailableTechnician = -1;
	}

	@Override
	public void setIdOfAvailableTechnician(int availableTechnicianId) {
		this.idOfAvailableTechnician = availableTechnicianId;
	}

	@Override
	public int getIdOfAvailableTechnician() {
		return this.idOfAvailableTechnician;
	}

	public void update(int availableTechnicianId) {
		System.out.println("Pojawil sie wolny serwisant o id: " + availableTechnicianId);
		setIdOfAvailableTechnician(availableTechnicianId);
	}

}