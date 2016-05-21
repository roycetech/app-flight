package ph.rye.flight.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ph.rye.common.lang.ObjectUtil;
import ph.rye.flight.common.Constant;
import ph.rye.flight.model.AirportLocation;
import ph.rye.flight.model.FlightClass;
import ph.rye.flight.model.Gender;
import ph.rye.flight.model.PilotRank;

/**
 * Application Lifecycle Listener implementation class WebContextListener
 *
 */
@WebListener
public class WebContextListener implements ServletContextListener {


    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent arg0) {}

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent event) {
        final ServletContext servletContext = event.getServletContext();

        servletContext.setAttribute(
            Constant.Param.PILOT_RANKS,
            ObjectUtil.toValueDispMap(PilotRank.class));

        servletContext.setAttribute(
            Constant.Param.AIRPORTS,
            ObjectUtil.toValueDispMap(AirportLocation.class));

        servletContext.setAttribute(
            Constant.Param.GENDERS,
            ObjectUtil.toValueDispMap(Gender.class));

        servletContext.setAttribute(
            Constant.Param.FLIGHT_CLASSES,
            ObjectUtil.toValueDispMap(FlightClass.class));

    }

}
