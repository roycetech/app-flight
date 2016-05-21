package ph.rye.flight.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ph.rye.flight.model.Airplane;

/**
 * Session Bean implementation class AirplaneBean
 */
@Stateless
@LocalBean
public class AirplaneBean extends AbstractBean<Airplane> {


    /** {@inheritDoc} */
    @Override
    Class<Airplane> getEntityClass() {
        return Airplane.class;
    }

    //    public void addAirplane(final Airplane airplane) {
    //        super.addEntity(airplane);
    //    }
    //
    //    public List<Airplane> getAllAirplanes() {
    //        final TypedQuery<Airplane> query = entityManager.createQuery(
    //            new JQLBuilder().select("a").from("Airplane").build(),
    //            Airplane.class);
    //
    //        return query.getResultList();
    //    }
    //
    //    public long getAirplaneCount() {
    //        final Query query = entityManager.createQuery(
    //            new JQLBuilder().select("COUNT(a.id)").from("Airplane").build());
    //
    //        return (long) query.getSingleResult();
    //    }
    //
    //    @Override
    //    public Airplane findById(final Integer passengerId) {
    //        final TypedQuery<Airplane> airplaneQuery = entityManager
    //            .createNamedQuery("Airplane.findById", Airplane.class);
    //        airplaneQuery.setParameter("id", passengerId);
    //        return airplaneQuery.getSingleResult();
    //    }
    //
    //    @Override
    //    public int deleteByIds(final Integer... airplaneIds) {
    //
    //
    //        final CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
    //        final CriteriaDelete<Airplane> critDelete =
    //                critBuilder.createCriteriaDelete(Airplane.class);
    //        critDelete.from(Airplane.class).in(Arrays.asList(airplaneIds));
    //
    //        final Query deleteQuery = entityManager.createQuery(critDelete);
    //        return deleteQuery.executeUpdate();
    //    }


}
