package ru.surpavel.university.dao;

import java.util.List;

import ru.surpavel.university.domain.Student;

public interface StudentDao {

    Student create(Student student);

    List<Student> findAll();

    Student findById(int id);

    List<Student> findByGroupId(int groupId);

    Student update(Student student);

    void delete(int id);

}
