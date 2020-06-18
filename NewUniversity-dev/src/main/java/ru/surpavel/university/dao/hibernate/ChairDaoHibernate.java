package ru.surpavel.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ru.surpavel.university.dao.ChairDao;
import ru.surpavel.university.dao.DaoException;
import ru.surpavel.university.domain.Chair;
import ru.surpavel.university.domain.Group;

@Repository
@Qualifier("hibernate")
public class ChairDaoHibernate implements ChairDao {
    private static final Logger log = LogManager.getLogger(ChairDaoHibernate.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Chair create(Chair chair) {
        log.debug("Creating " + chair);
        try {
            em.persist(chair);
            log.info(chair + " was created");
        } catch (RuntimeException e) {
            log.error("Can't create " + chair, e);
            throw new DaoException("Can't create " + chair, e);
        }
        return chair;
    }

    @Override
    public List<Chair> findAll() {
        log.debug("Looking for all chairs");
        List<Chair> chairs;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Chair> query = cb.createQuery(Chair.class);
            query.from(Chair.class);
            chairs = em.createQuery(query).getResultList();
            log.debug(String.format("Found %d chairs.", chairs.size()));
        } catch (RuntimeException e) {
            log.error("Can't find all chairs ", e);
            throw new DaoException("Can't find all chairs ", e);
        }
        return chairs;
    }

    @Override
    public Chair findById(int id) {
        log.debug("Looking for chair with id " + id);
        Chair chair;
        try {
            chair = em.find(Chair.class, id);
            chair.getGroups().size();
            log.debug("Found " + chair);
        } catch (RuntimeException e) {
            log.error("Can't find chair with id " + id, e);
            throw new DaoException("Can't find chair with id " + id, e);
        }
        return chair;
    }

    @Override
    public Chair update(Chair chair) {
        log.debug("Updating chair with id " + chair.getId());
        try {
            em.merge(chair);
            log.info(String.format("Chair with id %s was updated. New data: %s", chair.getId(), chair));
        } catch (RuntimeException e) {
            log.error("Can't update chair with id " + chair.getId(), e);
            throw new DaoException("Can't update chair with id " + chair.getId(), e);
        }
        return chair;
    }

    @Override
    public void delete(int id) {
        log.debug("Deleting chair with id " + id);
        try {
            Chair chair = em.find(Chair.class, id);
            List<Group> groups = chair.getGroups();
            for (Group group : groups) {
                group.setChair(null);
            }
            log.trace("ChairId " + id + " was removed from " + groups.size() + " groups");
            em.remove(chair);
            log.info("Chair with id " + id + " was deleted");
        } catch (RuntimeException e) {
            log.error("Can't delete chair with id " + id, e);
            throw new DaoException("Can't delete chair with id " + id, e);
        }
    }

}
