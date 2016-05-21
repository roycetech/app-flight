package ph.rye.flight.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import ph.rye.flight.common.Constant;
import ph.rye.flight.controller.util.JQLBuilder;
import ph.rye.flight.model.Passenger;

/**
 * Session Bean implementation class PassengerBean
 */
@Stateless
@LocalBean
public class PassengerBean {


    @PersistenceContext(unitName = Constant.PERSISTENCE_UNIT)
    private EntityManager entityManager;


    public void addPassenger(final Passenger passenger) {
        entityManager.persist(passenger);
    }

    public List<Passenger> getAllPassengers() {
        final TypedQuery<Passenger> query = entityManager.createQuery(
            new JQLBuilder().select("p").from("Passenger", "p").build(),
            Passenger.class);

        return query.getResultList();
    }

    public long getPassengerCount() {
        final Query query = entityManager.createQuery(
            new JQLBuilder()
                .select()
                .count("p.id")
                .from("Passenger", "p")
                .build());
        return (long) query.getSingleResult();
    }

    public Passenger findById(final Integer passengerId) {
        final TypedQuery<Passenger> passengerQuery = entityManager
            .createNamedQuery("Passenger.findById", Passenger.class);
        passengerQuery.setParameter("id", passengerId);
        try {
            return passengerQuery.getSingleResult();
        } catch (final NoResultException e) {
            return null;
        }
    }

    public Passenger updatePasenger(final Passenger updated) {
        assert updated != null;

        final Passenger dbPassenger = findById(updated.getId());
        if (dbPassenger == null) {
            return null;
        } else {
            entityManager.merge(updated);
        }

        return updated;
    }

    public boolean deletePasenger(final Integer passengerId) {
        assert passengerId != null;

        final Passenger passenger =
                entityManager.find(Passenger.class, passengerId);
        if (passenger == null) {
            return false;
        } else {
            entityManager.remove(passenger);
        }

        return true;
    }

    public int deleteByIds(final Integer... passengerIds) {
        final CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<Passenger> critDelete =
                critBuilder.createCriteriaDelete(Passenger.class);
        critDelete.from(Passenger.class).in(Arrays.asList(passengerIds));

        final Query deleteQuery = entityManager.createQuery(critDelete);
        return deleteQuery.executeUpdate();
    }


}
