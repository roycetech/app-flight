package ph.rye.flight.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import ph.rye.flight.common.FlightAppException;
import ph.rye.flight.model.Airplane;
import ph.rye.flight.model.FlightClass;
import ph.rye.flight.model.Gender;
import ph.rye.flight.model.Passenger;
import ph.rye.flight.model.Pilot;
import ph.rye.flight.model.PilotRank;
import ph.rye.logging.OneLogger;

/**
 * Session Bean implementation class CommonLOVBean
 */
@Startup
@Singleton
@LocalBean
public class CommonLOVBean {


    @EJB
    private PilotBean pilotBean;

    @EJB
    private AirplaneBean airplaneBean;

    @EJB
    private PassengerBean passengerBean;


    private static final DateFormat DT_FMT =
            new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());


    private final static OneLogger LOG1 = OneLogger.getInstance();


    private boolean constructed;

    @PostConstruct
    public void init() {
        if (constructed) {
            throw new FlightAppException(
                "Post Construct called more than once!");
        } else {
            constructed = true;

            final Pilot pilot1 = new Pilot();
            pilot1.setFirstName("Michelle");
            pilot1.setLastName("Remulla");
            pilot1.setLicense(1111111);
            pilot1.setRank(PilotRank.Captain);
            pilotBean.addEntity(pilot1);


            final Passenger passenger = new Passenger();
            passenger.setLastName("Gosling");
            passenger.setFirstName("James");
            try {
                synchronized (CommonLOVBean.class) {
                    passenger.setDob(DT_FMT.parse("05/19/1955"));
                }
            } catch (final ParseException e) {
                LOG1.error(e);
            }
            passenger.setGender(Gender.Male);
            passenger.setFlightClass(FlightClass.Coach);
            passengerBean.addPassenger(passenger);

            final Airplane airplane = new Airplane();
            airplane.setPlaneMake("Boeing");
            airplane.setModelName("747");
            airplane.setSeatingCapacity(300);
            airplaneBean.addEntity(airplane);

        }
    }

}
