package ph.rye.flight.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@Entity
@NamedQuery(name = "Flight.findById", query = "SELECT f from Flight f WHERE f.id = :id")
public class Flight implements Serializable {


    @Transient
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AirportLocation flightOrigin;
    @Enumerated(EnumType.STRING)
    private AirportLocation flightDestination;

    private Float price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date flightTime;


    //    @OneToOne(cascade = {
    //            CascadeType.PERSIST,
    //            CascadeType.REMOVE })
    @OneToOne
    @JoinColumn(name = "airplane_fk")
    private Airplane airplaneDetail;

    @OneToMany(mappedBy = "flightForPilot")
    private List<Pilot> pilots;

    // @formatter:off
    @ManyToMany
    @JoinTable(name = "flight_passenger"
        , joinColumns = @JoinColumn(name = "flight_fk")
        , inverseJoinColumns = @JoinColumn(name = "passenger_fk"))
    // @formatter:on
    private List<Passenger> passengers;


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }


    public AirportLocation getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(final AirportLocation flightDestination) {
        this.flightDestination = flightDestination;
    }

    public AirportLocation getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(final AirportLocation flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(final Float price) {
        this.price = price;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(final Date flightTime) {
        this.flightTime = flightTime;
    }

    public String getAirplaneDisp() {
        return airplaneDetail == null ? "No airplane assigned."
                : airplaneDetail.getNameDisp();
    }

    /**
     * @return the airplaneDetail
     */
    public Airplane getAirplaneDetail() {
        return airplaneDetail;
    }

    /**
     * @param airplaneDetail the airplaneDetail to set
     */
    public void setAirplaneDetail(final Airplane airplaneDetail) {
        this.airplaneDetail = airplaneDetail;
    }

    /**
     * @return the pilots
     */
    public List<Pilot> getPilots() {
        if (pilots == null) {
            pilots = new ArrayList<>();
        }
        return pilots;
    }

    /**
     * @param pilots the pilots to set
     */
    public void setPilots(final List<Pilot> pilots) {
        this.pilots = pilots;
    }

    /**
     * @return the passengers
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * @param passengers the passengers to set
     */
    public void setPassengers(final List<Passenger> passengers) {
        this.passengers = passengers;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Flight [id=" + id + ", flightDestination=" + flightDestination
                + ", flightOrigin=" + flightOrigin + ", price=" + price
                + ", flightTime=" + flightTime + ", airplaneDetail="
                + airplaneDetail + "]";
    }

}
