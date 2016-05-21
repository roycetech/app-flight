package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.FlightCancelAction;
import ph.rye.flight.controller.action.FlightSaveAction;
import ph.rye.flight.controller.action.LoadFlightDetailAction;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.flight.service.FlightBean;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class Pilot
 */
@WebServlet(FlightDetail.URL)
@RequestAction(LoadFlightDetailAction.class)
@SubmitAction({
        FlightSaveAction.class,
        FlightCancelAction.class })
public class FlightDetail extends SafServlet {


    /** */
    private static final long serialVersionUID = 1L;


    public static final String URL = "/flight";

    @EJB
    FlightBean flightBean;

    @EJB
    AirplaneBean airplaneBean;

    @EJB
    PilotBean pilotBean;

}
