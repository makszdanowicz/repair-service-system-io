package com.pwr.testsfitnessefixture;

import com.pwr.model.RequestDAO;
import com.pwr.model.TechnicianDAO;
import com.pwr.model.ClientDAO;
import com.pwr.presenter.AssignmentFacade;
import com.pwr.view.IAdminView;
import com.pwr.view.IClientView;
import com.pwr.view.ITechnicianView;
import mockit.Mocked;
import fit.Fixture;
public class SetUp extends Fixture{
    static AssignmentFacade assignmentFacade;
    @Mocked
    private ClientDAO clientDAO;

    @Mocked
    private TechnicianDAO technicianDAO;

    @Mocked
    private RequestDAO requestDAO;

    @Mocked
    private IAdminView adminView;

    @Mocked
    private IClientView clientView;

    @Mocked
    private ITechnicianView technicianView;

    public SetUp(){
        assignmentFacade = new AssignmentFacade(requestDAO, technicianDAO, clientDAO, adminView, clientView, technicianView);
    }
}
