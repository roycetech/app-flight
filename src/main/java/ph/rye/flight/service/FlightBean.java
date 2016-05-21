/**
 *   Copyright 2016 Royce Remulla
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ph.rye.flight.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

import ph.rye.flight.common.Constant;
import ph.rye.flight.controller.util.JQLBuilder;
import ph.rye.flight.model.Airplane;
import ph.rye.flight.model.Flight;
import ph.rye.flight.model.Pilot;

/**
 * Session Bean implementation class FlightBean
 */
@Stateless
@LocalBean
public class FlightBean {


    @PersistenceContext(unitName = Constant.PERSISTENCE_UNIT)
    private EntityManager entityManager;


    public void addFlight(final Flight flight, final Airplane airplane) {
        entityManager.persist(flight);
        flight.setAirplaneDetail(airplane);
    }

    public void addPilotToFlight(final Integer pilotId,
                                 final Integer flightId) {

        final Flight flight = findById(flightId);

        final TypedQuery<Pilot> pilotQuery =
                entityManager.createNamedQuery("Pilot.findById", Pilot.class);
        pilotQuery.setParameter("id", pilotId);
        final Pilot pilot = pilotQuery.getSingleResult();

        final List<Pilot> pilotList = flight.getPilots();
        pilotList.add(pilot);
        pilot.setFlightForPilot(flight);
    }

    public List<Flight> getAllFlights() {
        final TypedQuery<Flight> query = entityManager.createQuery(
            new JQLBuilder().select("f").from("Flight", "f").build(),
            Flight.class);

        return query.getResultList();
    }

    public long getFlightCount() {
        final Query query = entityManager.createQuery(
            new JQLBuilder().select("COUNT(f.id)").from("Flight", "f").build());

        return (long) query.getSingleResult();
    }

    public Flight findById(final Integer flightId) {
        return entityManager.find(Flight.class, flightId);
    }

    public int deleteByIds(final Integer... flightIds) {
        final CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<Flight> critDelete =
                critBuilder.createCriteriaDelete(Flight.class);
        critDelete.from(Flight.class).in(Arrays.asList(flightIds));

        final Query deleteQuery = entityManager.createQuery(critDelete);
        return deleteQuery.executeUpdate();
    }


}
