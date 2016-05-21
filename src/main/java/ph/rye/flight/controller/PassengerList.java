package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.LoadPassengerListAction;
import ph.rye.flight.controller.action.PassengerAddAction;
import ph.rye.flight.controller.action.PassengerDeleteAction;
import ph.rye.flight.service.PassengerBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class FlightsServlet
 */
@RequestAction(LoadPassengerListAction.class)
@SubmitAction({
        PassengerAddAction.class,
        PassengerDeleteAction.class })
@WebServlet(PassengerList.URL)
public class PassengerList extends SafServlet {


    private static final long serialVersionUID = 1L;


    public static final String URL = "/passengers";

    @EJB
    PassengerBean passengerBean;


}
