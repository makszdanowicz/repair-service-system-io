package com.pwr.model;

import com.pwr.model.Technician;

import java.util.List;

public interface TechnicianDAO {

	/**
	 * 
	 * @param technician
	 */
	void addTechnician(Technician technician);

	/**
	 * 
	 * @param updatedTechnician
	 */
	void updateTechnician(Technician updatedTechnician);

	/**
	 * 
	 * @param technicianId
	 */
	Technician getTechnicianById(int technicianId);

	List<Technician> getAllTechnicians();

	/**
	 * 
	 * @param technician
	 */
	void deleteTechnician(Technician technician);

}