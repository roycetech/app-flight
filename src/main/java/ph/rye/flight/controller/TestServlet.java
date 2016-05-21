package ph.rye.flight.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ph.rye.flight.model.Pilot;
import ph.rye.flight.service.PilotBean;

/**
 * Servlet implementation class AddFlightServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;


    @EJB
    private PilotBean pilotBean;


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {

        final List<Pilot> pilots = pilotBean.getPilotsExcept(2);

        response
            .getWriter()
            .append("Served at: ")
            .append(request.getContextPath())
            .append("\n")
            .append(pilots.toString());
    }

}
