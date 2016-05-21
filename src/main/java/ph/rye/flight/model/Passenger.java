package ph.rye.flight.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
@XmlRootElement
@NamedQuery(name = "Passenger.findById", query = "SELECT p FROM Passenger p WHERE p.id = :id")
public class Passenger implements Serializable {


    @Transient
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;


    @ManyToMany(mappedBy = "passengers")
    private List<Flight> flights;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }


    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }


    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    public String getDobDisp() {
        return new SimpleDateFormat("MMM/dd/yyyy HH:mm", Locale.getDefault())
            .format(getDob());
    }


    /**
     * @param dob the dob to set
     */
    public void setDob(final Date dob) {
        this.dob = dob;
    }


    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }


    /**
     * @param gender the gender to set
     */
    public void setGender(final Gender gender) {
        this.gender = gender;
    }


    /**
     * @return the flightClass
     */
    public FlightClass getFlightClass() {
        return flightClass;
    }


    /**
     * @param flightClass the flightClass to set
     */
    public void setFlightClass(final FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    /**
     * @return the flights
     */
    public List<Flight> getFlights() {
        return flights;
    }


    /**
     * @param flights the flights to set
     */
    public void setFlights(final List<Flight> flights) {
        this.flights = flights;
    }


    public void update(final Passenger passenger) {
        if (passenger.getLastName() != null) {
            this.setLastName(passenger.getLastName());
        }
        if (passenger.getFirstName() != null) {
            this.setFirstName(passenger.getFirstName());
        }
        if (passenger.getDob() != null) {
            this.setDob(passenger.getDob());
        }
        if (passenger.getGender() != null) {
            this.setGender(passenger.getGender());
        }

    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Passenger [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dob=" + dob + ", gender="
                + gender + ", flightClass=" + flightClass + "]";
    }


}
