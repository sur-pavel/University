package ru.surpavel.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ru.surpavel.university.dao.DaoException;
import ru.surpavel.university.dao.GroupDao;
import ru.surpavel.university.domain.Chair_;
import ru.surpavel.university.domain.Group;
import ru.surpavel.university.domain.Group_;
import ru.surpavel.university.domain.Student;

@Repository
@Qualifier("hibernate")
public class GroupDaoHibernate implements GroupDao {
    private static final Logger log = LogManager.getLogger(GroupDaoHibernate.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Group create(Group group) {
        log.debug("Creating " + group);
        try {
            em.persist(group);
            log.info(group + " was created");
        } catch (RuntimeException e) {
            log.error("Can't create " + group, e);
            throw new DaoException("Can't create " + group, e);
        }
        return group;
    }

    @Override
    public List<Group> findAll() {
        log.debug("Looking for all groups");
        List<Group> groups;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Group> query = cb.createQuery(Group.class);
            query.from(Group.class);
            groups = em.createQuery(query).getResultList();
            log.debug(String.format("Found %d groups.", groups.size()));
        } catch (RuntimeException e) {
            log.error("Can't find all groups ", e);
            throw new DaoException("Can't find all groups ", e);
        }
        return groups;
    }

    @Override
    public Group findById(int id) {
        log.debug("Looking for group with id " + id);
        Group group;
        try {
            group = em.find(Group.class, id);
            group.getStudents().size();
        } catch (RuntimeException e) {
            log.error("Can't find group with id " + id, e);
            throw new DaoException("Can't find group with id " + id, e);
        }
        return group;
    }

    @Override
    public List<Group> findByChairId(int chairId) {
        log.debug("Looking for groups with chairId " + chairId);
        List<Group> groups;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Group> query = cb.createQuery(Group.class);
            Root<Group> root = query.from(Group.class);
            Predicate condition = cb.equal(root.get(Group_.chair).get(Chair_.id), chairId);
            query.where(condition);
            groups = em.createQuery(query).getResultList();
            log.debug(String.format("Found %d groups with chairId %d", groups.size(), chairId));
        } catch (RuntimeException e) {
            log.error("Can't find groups with chairId " + chairId, e);
            throw new DaoException("Can't find groups with chairId " + chairId, e);
        }
        return groups;
    }

    @Override
    public Group update(Group group) {
        log.debug("Updating group with id " + group.getId());
        try {
            em.merge(group);
            log.info(String.format("Group with id %s was updated. New data: %s", group.getId(), group));
        } catch (RuntimeException e) {
            log.error("Can't update group with id " + group.getId(), e);
            throw new DaoException("Can't update group with id " + group.getId(), e);
        }
        return group;
    }

    @Override
    public void delete(int id) {
        log.debug("Deleting group with id " + id);
        try {
            Group group = em.find(Group.class, id);
            List<Student> students = group.getStudents();
            for (Student student : students) {
                student.setGroup(null);
            }
            log.trace("GroupId " + id + " was removed from " + students.size() + " students");
            em.remove(group);
            log.info("Group with id " + id + " was deleted");
        } catch (RuntimeException e) {
            log.error("Can't delete group with id " + id, e);
            throw new DaoException("Can't delete group with id " + id, e);
        }
    }
}
