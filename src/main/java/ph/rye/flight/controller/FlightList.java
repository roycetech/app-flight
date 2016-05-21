package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.FlightAddAction;
import ph.rye.flight.controller.action.FlightDeleteAction;
import ph.rye.flight.controller.action.LoadFlightListAction;
import ph.rye.flight.service.FlightBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class FlightsServlet
 */
@RequestAction(LoadFlightListAction.class)
@SubmitAction({
        FlightAddAction.class,
        FlightDeleteAction.class })
@WebServlet(FlightList.URL)
public class FlightList extends SafServlet {


    private static final long serialVersionUID = 1L;


    public static final String URL = "/flights";


    @EJB
    FlightBean flightBean;


}
