package ru.surpavel.university.dao;

import java.util.List;

import ru.surpavel.university.domain.Chair;

public interface ChairDao {

    Chair create(Chair chair);

    List<Chair> findAll();

    Chair findById(int id);

    Chair update(Chair chair);

    void delete(int id);

}
