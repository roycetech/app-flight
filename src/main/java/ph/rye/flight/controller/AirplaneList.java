package ph.rye.flight.controller;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import ph.rye.flight.controller.action.AirplaneAddAction;
import ph.rye.flight.controller.action.AirplaneDeleteAction;
import ph.rye.flight.controller.action.LoadAirplaneListAction;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.saf.SafServlet;
import ph.rye.saf.ann.RequestAction;
import ph.rye.saf.ann.SubmitAction;

/**
 * Servlet implementation class Pilot
 */
@WebServlet(AirplaneList.URL)
@RequestAction(LoadAirplaneListAction.class)
@SubmitAction({
        AirplaneAddAction.class,
        AirplaneDeleteAction.class })
public class AirplaneList extends SafServlet {


    /** */
    private static final long serialVersionUID = -6630375514981912933L;


    public static final String URL = "/airplanes";

    @EJB
    AirplaneBean airplaneBean;

}
