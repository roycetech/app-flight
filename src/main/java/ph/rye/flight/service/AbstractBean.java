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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ph.rye.flight.common.Constant;

/**
 * @author royce
 *
 */
public abstract class AbstractBean<T> {


    @PersistenceContext(unitName = Constant.PERSISTENCE_UNIT)
    EntityManager em;


    abstract Class<T> getEntityClass();

    public void addEntity(final T entity) {
        em.persist(entity);
    }

    public List<T> getAllEntities() {
        final CriteriaBuilder critBuilder = em.getCriteriaBuilder();

        final CriteriaQuery<T> critQuery =
                critBuilder.createQuery(getEntityClass());

        final TypedQuery<T> typedQuery = em.createQuery(critQuery);
        return typedQuery.getResultList();
    }

    public long getEntityCount() {
        final CriteriaBuilder critBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> critQuery =
                critBuilder.createQuery(Long.class);
        critQuery.select(critBuilder.count(critQuery.from(getEntityClass())));
        return em.createQuery(critQuery).getSingleResult();
    }

    public T findById(final Integer entityId) {
        return em.find(getEntityClass(), entityId);
    }

    public int deleteByIds(final Integer... entityIds) {
        final CriteriaBuilder critBuilder = em.getCriteriaBuilder();
        final CriteriaDelete<T> critDelete =
                critBuilder.createCriteriaDelete(getEntityClass());
        final Root<T> table = critDelete.from(getEntityClass());
        critDelete.where(
            critBuilder.not(table.get("id").in(Arrays.asList(entityIds))));
        return em.createQuery(critDelete).executeUpdate();
    }

    public boolean deleteEntity(final Integer entityId) {
        assert entityId != null;

        final T entity = em.find(getEntityClass(), entityId);
        if (entity == null) {
            return false;
        } else {
            em.remove(entity);
        }

        return true;
    }


}
