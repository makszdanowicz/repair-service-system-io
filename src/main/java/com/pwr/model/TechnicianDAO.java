package com.pwr.model;

import com.pwr.model.Technician;

import java.util.List;

public interface TechnicianDAO {

	/**
	 * 
	 * @param technician
	 */
	void addTechnician(Technician technician);


	void updateTechnician(int idOfUpdatedTechnician, int idOfRequest);

	/**
	 * 
	 * @param technicianId
	 */
	Technician getTechnicianById(int technicianId);
	Technician getTechnicianByRequestId(int requestId);

	List<Technician> getAllTechnicians();

	/**
	 * 
	 * @param technician
	 */
	void deleteTechnician(Technician technician);

}