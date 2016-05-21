package ph.rye.flight.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ph.rye.flight.model.Pilot;

/**
 * Session Bean implementation class PilotBean
 */
@Stateless
@LocalBean
public class PilotBean extends AbstractBean<Pilot> {


    /** {@inheritDoc} */
    @Override
    Class<Pilot> getEntityClass() {
        return Pilot.class;
    }

    /**
     * This is to demonstrate the basic use of Criteria API for fetching result.
     *
     * {@inheritDoc}
     */
    @Override
    public Pilot findById(final Integer pilotId) {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Pilot> critPilot = builder.createQuery(Pilot.class);
        final Root<Pilot> pilot = critPilot.from(Pilot.class);
        critPilot.select(pilot).where(
            builder.equal(pilot.get("id").as(Integer.class), pilotId));

        return em.createQuery(critPilot).getSingleResult();
    }

    public List<Pilot> getPilotsExcept(final Integer... entityIds) {

        final CriteriaBuilder critBuilder = em.getCriteriaBuilder();

        final CriteriaQuery<Pilot> critQuery =
                critBuilder.createQuery(getEntityClass());

        final Root<Pilot> root = critQuery.from(getEntityClass());
        critQuery.select(root).where(
            critBuilder.not(root.get("id").in(Arrays.asList(entityIds))));

        final TypedQuery<Pilot> typedQuery = em.createQuery(critQuery);
        return typedQuery.getResultList();
    }
}
