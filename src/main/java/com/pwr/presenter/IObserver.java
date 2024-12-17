package com.pwr.presenter;

public interface IObserver {
	void setIdOfAvailableTechnician(int availableTechnicianId);
	int getIdOfAvailableTechnician();
	void update(int availableTechnicianId);
}