package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.LoadPilotListAction;
import ph.rye.flight.controller.action.PilotAddAction;
import ph.rye.flight.controller.action.PilotDeleteAction;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class Pilot
 */
@RequestAction(LoadPilotListAction.class)
@SubmitAction({
        PilotAddAction.class,
        PilotDeleteAction.class })
@WebServlet(PilotList.URL)
public class PilotList extends SafServlet {


    /** */
    private static final long serialVersionUID = 1L;


    public static final String URL = "/pilots";

    @EJB
    PilotBean pilotBean;

}
