package com.pwr.presenter;

import com.pwr.model.Technician;
import com.pwr.presenter.IObserver;

import java.util.List;

public class SystemObserver implements IObserver {

	private List<Technician> monitoredTechnicians;

	/**
	 * 
	 * @param monitoredTechnicians
	 */
	public SystemObserver(List<Technician> monitoredTechnicians) {
		// TODO - implement SystemObserver.SystemObserver
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param availableTechnicianId
	 */
	public void update(int availableTechnicianId) {
		// TODO - implement SystemObserver.update
		throw new UnsupportedOperationException();
	}

}