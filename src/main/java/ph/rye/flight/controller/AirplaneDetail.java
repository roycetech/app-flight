package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.AirplaneCancelAction;
import ph.rye.flight.controller.action.LoadAirplaneDetailAction;
import ph.rye.flight.controller.action.AirplaneSaveAction;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class Pilot
 */
@WebServlet(AirplaneDetail.URL)
@RequestAction(LoadAirplaneDetailAction.class)
@SubmitAction({
        AirplaneSaveAction.class,
        AirplaneCancelAction.class })
public class AirplaneDetail extends SafServlet {


    /** */
    private static final long serialVersionUID = 1L;


    public static final String URL = "/airplane";

    @EJB
    AirplaneBean airplaneBean;
}
