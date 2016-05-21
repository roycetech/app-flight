package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.LoadHomeAction;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.flight.service.FlightBean;
import ph.rye.flight.service.PassengerBean;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home") // Eclipse incorrectly places the constant code if you don't hard code.
@RequestAction(LoadHomeAction.class)
public class Home extends SafServlet {


    private static final long serialVersionUID = 1L;


    public static final String URL = "/Home";


    @EJB
    PilotBean pilotBean;

    @EJB
    FlightBean flightBean;

    @EJB
    PassengerBean passengerBean;

    @EJB
    AirplaneBean airplaneBean;

}
