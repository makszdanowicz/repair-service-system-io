package com.pwr.testsfitnessefixture;

import com.pwr.model.Technician;
import com.pwr.presenter.AssignmentFacade;
import fit.ColumnFixture;

import java.util.ArrayList;
import java.util.List;

public class TestGetAvailableTechnicians extends ColumnFixture {
    public Integer[] techniciansId;
    public boolean[] techniciansAvailability;

    public boolean getAvailableTechnicians(){
        AssignmentFacade assignmentFacade = new AssignmentFacade(null, null, null, null, null, null);
        List<Technician> allTechnicians = new ArrayList<>();
        for(int i =  0; i < techniciansId.length; i++){
            int technicianId = techniciansId[i];
            boolean availability = techniciansAvailability[i];
            Technician technician = new Technician(technicianId, null, availability, 0);
            allTechnicians.add(technician);
        }
        return !assignmentFacade.getAvailableTechnicians(allTechnicians).isEmpty();
    }
}
