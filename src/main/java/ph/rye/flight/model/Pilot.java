package ph.rye.flight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Pilot
 *
 */
@NamedQuery(name = "Pilot.findById", query = "SELECT p FROM Pilot p WHERE p.id = :id")
@Entity
public class Pilot implements Serializable {


    @Transient
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;


    private Integer license;

    @Enumerated(EnumType.STRING)
    private PilotRank rank;

    @ManyToOne
    @JoinColumn(name = "flight_fk")
    private Flight flightForPilot;


    public Integer getLicense() {
        return license;
    }

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


    public void setLicense(final Integer license) {
        this.license = license;
    }

    public PilotRank getRank() {
        return rank;
    }

    public String getRankDisp() {
        return rank.toString();
    }


    public void setRank(final PilotRank rank) {
        this.rank = rank;
    }


    /**
     * @return the flightForPilot
     */
    public Flight getFlightForPilot() {
        return flightForPilot;
    }


    /**
     * @param flightForPilot the flightForPilot to set
     */
    public void setFlightForPilot(final Flight flightForPilot) {
        this.flightForPilot = flightForPilot;
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    public String getNameDisp() {
        return String.format("%s %s(%s)", new Object[] {
                getFirstName(),
                getLastName(),
                getRankDisp() });
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


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Pilot [license=" + license + ", rank=" + rank
                + ", flightForPilot=" + flightForPilot + ", toString()="
                + super.toString() + "]";
    }

}
