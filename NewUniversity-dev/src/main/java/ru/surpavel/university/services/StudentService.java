package ru.surpavel.university.services;

import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.surpavel.university.dao.DaoException;
import ru.surpavel.university.dao.StudentDao;
import ru.surpavel.university.domain.Student;

@Service
@Transactional
public class StudentService {
    private static final Logger log = LogManager.getLogger(StudentService.class.getName());

    private StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("hibernate") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student create(Student student) {
        try {
            student = studentDao.create(student);
        } catch (DaoException e) {
            log.error("Can't create " + student, e);
            throw new ServiceException("Can't create " + student, e);
        }
        return student;
    }

    public Student findById(int id) {
        Student student;
        try {
            student = studentDao.findById(id);
        } catch (DaoException e) {
            log.error("Can't find student with id " + id, e);
            throw new ServiceException("Can't find student with id " + id, e);
        }
        return student;
    }

    public List<Student> findAll() {
        List<Student> students;
        try {
            students = studentDao.findAll();
            students.sort(Comparator.comparing(Student::getId));
        } catch (DaoException e) {
            log.error("Can't find all students", e);
            throw new ServiceException("Can't find all students", e);
        }
        return students;
    }

    public List<Student> findByGroupId(int groupId) {
        List<Student> students;
        try {
            students = studentDao.findByGroupId(groupId);
        } catch (DaoException e) {
            log.error("Can't find students with groupId " + groupId, e);
            throw new DaoException("Can't find students with groupId " + groupId, e);
        }
        return students;
    }

    public Student update(Student student) {
        try {
            student = studentDao.update(student);
        } catch (DaoException e) {
            log.error("Can't update " + student, e);
            throw new ServiceException("Can't update " + student, e);
        }
        return student;
    }

    public void delete(int id) {
        try {
            studentDao.delete(id);
        } catch (DaoException e) {
            log.error("Can't delete student with id " + id, e);
            throw new ServiceException("Can't delete student with id " + id, e);
        }
    }

}
