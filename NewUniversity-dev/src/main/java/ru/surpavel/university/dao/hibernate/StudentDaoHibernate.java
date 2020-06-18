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
import ru.surpavel.university.dao.StudentDao;
import ru.surpavel.university.domain.Group_;
import ru.surpavel.university.domain.Student;
import ru.surpavel.university.domain.Student_;

@Repository
@Qualifier("hibernate")
public class StudentDaoHibernate implements StudentDao {
    private static final Logger log = LogManager.getLogger(StudentDaoHibernate.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Student create(Student student) {
        log.debug("Creating " + student);
        try {
            em.persist(student);
            log.info(student + " was created");
        } catch (RuntimeException e) {
            log.error("Can't create " + student, e);
            throw new DaoException("Can't create " + student, e);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        log.debug("Looking for all students");
        List<Student> students;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Student> query = cb.createQuery(Student.class);
            query.from(Student.class);
            students = em.createQuery(query).getResultList();
            log.debug(String.format("Found %d students.", students.size()));
        } catch (RuntimeException e) {
            log.error("Can't find all students ", e);
            throw new DaoException("Can't find all students ", e);
        }
        return students;
    }

    @Override
    public Student findById(int id) {
        log.debug("Looking for student with id " + id);
        Student student;
        try {
            student = em.find(Student.class, id);
            log.debug("Found " + student);
        } catch (RuntimeException e) {
            log.error("Can't find student with id " + id, e);
            throw new DaoException("Can't find student with id " + id, e);
        }
        return student;
    }

    @Override
    public List<Student> findByGroupId(int groupId) {
        log.debug("Looking for students with groupId " + groupId);
        List<Student> students;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Student> query = cb.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);
            Predicate condition = cb.equal(root.get(Student_.group).get(Group_.id), groupId);
            query.where(condition);
            students = em.createQuery(query).getResultList();
            log.debug(String.format("Found %d students with groupId %d", students.size(), groupId));
        } catch (RuntimeException e) {
            log.error("Can't find students with groupId " + groupId, e);
            throw new DaoException("Can't find students with groupId " + groupId, e);
        }
        return students;
    }

    @Override
    public Student update(Student student) {
        log.debug("Updating student with id " + student.getId());
        try {
            em.merge(student);
            log.info(String.format("Student with id %s was updated. New data: %s", student.getId(), student));
        } catch (RuntimeException e) {
            log.error("Can't update student with id " + student.getId(), e);
            throw new DaoException("Can't update student with id " + student.getId(), e);
        }
        return student;
    }

    @Override
    public void delete(int id) {
        log.debug("Deleting student with id " + id);
        try {
            Student student = em.find(Student.class, id);
            em.remove(student);
            log.info("Student with id " + id + " was deleted");
        } catch (RuntimeException e) {
            log.error("Can't delete student with id " + id, e);
            throw new DaoException("Can't delete student with id " + id, e);
        }
    }

}
