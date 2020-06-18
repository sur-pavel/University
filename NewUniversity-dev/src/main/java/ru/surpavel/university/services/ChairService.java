package ru.surpavel.university.services;

import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.surpavel.university.dao.ChairDao;
import ru.surpavel.university.dao.DaoException;
import ru.surpavel.university.domain.Chair;

@Service
@Transactional
public class ChairService {
    private static final Logger log = LogManager.getLogger(ChairService.class.getName());

    private ChairDao chairDao;

    @Autowired
    public ChairService(@Qualifier("hibernate") ChairDao chairDao) {
        this.chairDao = chairDao;
    }

    public Chair create(Chair chair) {
        try {
            chair = chairDao.create(chair);
        } catch (DaoException e) {
            log.error("Can't create " + chair, e);
            throw new ServiceException("Can't create " + chair, e);
        }
        return chair;
    }

    public Chair findById(int id) {
        Chair chair;
        try {
            chair = chairDao.findById(id);
        } catch (DaoException e) {
            log.error("Can't find chair with id " + id, e);
            throw new ServiceException("Can't find chair with id " + id, e);
        }
        return chair;
    }

    public List<Chair> findAll() {
        List<Chair> chairs;
        try {
            chairs = chairDao.findAll();
            chairs.sort(Comparator.comparing(Chair::getId));
        } catch (DaoException e) {
            log.error("Can't find all chairs", e);
            throw new ServiceException("Can't find all chairs", e);
        }
        return chairs;
    }

    public Chair update(Chair chair) {
        try {
            chair = chairDao.update(chair);
        } catch (DaoException e) {
            log.error("Can't update " + chair, e);
            throw new ServiceException("Can't update " + chair, e);
        }
        return chair;
    }

    public void delete(int id) {
        try {
            chairDao.delete(id);
        } catch (DaoException e) {
            log.error("Can't delete chair with id " + id, e);
            throw new ServiceException("Can't delete chair with id " + id, e);
        }
    }

}
