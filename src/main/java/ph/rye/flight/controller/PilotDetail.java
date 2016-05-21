package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.PilotCancelAction;
import ph.rye.flight.controller.action.LoadPilotDetailAction;
import ph.rye.flight.controller.action.PilotSaveAction;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class Pilot
 */
@WebServlet(PilotDetail.URL)
@RequestAction(LoadPilotDetailAction.class)
@SubmitAction({
        PilotSaveAction.class,
        PilotCancelAction.class })
public class PilotDetail extends SafServlet {


    /** */
    private static final long serialVersionUID = 1L;


    public static final String URL = "/pilot";

    @EJB
    PilotBean pilotBean;
}
