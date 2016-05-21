package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.PassengerCancelAction;
import ph.rye.flight.controller.action.LoadPassengerDetailAction;
import ph.rye.flight.controller.action.PassengerSaveAction;
import ph.rye.flight.service.PassengerBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class Pilot
 */
@WebServlet(PassengerDetail.URL)
@RequestAction(LoadPassengerDetailAction.class)
@SubmitAction({
        PassengerSaveAction.class,
        PassengerCancelAction.class })
public class PassengerDetail extends SafServlet {


    /** */
    private static final long serialVersionUID = 1L;


    public static final String URL = "/passenger";

    @EJB
    PassengerBean passengerBean;


}
