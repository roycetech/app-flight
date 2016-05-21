package ph.rye.flight.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Airplane
 *
 */
@Entity
@NamedQuery(name = "Airplane.findById", query = "SELECT a FROM Airplane a WHERE a.id = :id")
public class Airplane implements Serializable {


    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String planeMake;
    private String modelName;
    private Integer seatingCapacity;


    @SuppressWarnings("PMD.UnusedPrivateField") /* FP Used by JPA. */
    @OneToOne(mappedBy = "airplaneDetail")
    private Flight flight;


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPlaneMake() {
        return planeMake;
    }

    public void setPlaneMake(final String planeMake) {
        this.planeMake = planeMake;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(final String modelName) {
        this.modelName = modelName;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getNameDisp() {
        return getPlaneMake() + " " + getModelName() + "(" + id + ")";
    }

    public void setSeatingCapacity(final Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }


    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Airplane [id=" + id + ", planeMake=" + planeMake
                + ", modelName=" + modelName + ", seatingCapacity="
                + seatingCapacity + "]";
    }

}
