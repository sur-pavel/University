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
import ru.surpavel.university.dao.GroupDao;
import ru.surpavel.university.domain.Group;

@Service
@Transactional
public class GroupService {
    private static final Logger log = LogManager.getLogger(GroupService.class.getName());

    private GroupDao groupDao;

    @Autowired
    public GroupService(@Qualifier("hibernate") GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Group create(Group group) {
        try {
            group = groupDao.create(group);
        } catch (DaoException e) {
            log.error("Can't create " + group, e);
            throw new ServiceException("Can't create " + group, e);
        }
        return group;
    }

    public List<Group> findAll() {
        List<Group> groups;
        try {
            groups = groupDao.findAll();
            groups.sort(Comparator.comparing(Group::getId));
        } catch (DaoException e) {
            log.error("Can't find all groups", e);
            throw new ServiceException("Can't find all groups", e);
        }
        return groups;
    }

    public List<Group> findByChairId(int chairId) {
        List<Group> groups;
        try {
            groups = groupDao.findByChairId(chairId);
            groups.sort(Comparator.comparing(Group::getId));
        } catch (DaoException e) {
            log.error("Can't find groups with chairId " + chairId, e);
            throw new ServiceException("Can't find groups with chairId " + chairId, e);
        }
        return groups;
    }

    public Group findById(int id) {
        Group group;
        try {
            group = groupDao.findById(id);
        } catch (DaoException e) {
            log.error("Can't find group with id " + id, e);
            throw new ServiceException("Can't find group with id " + id, e);
        }
        return group;
    }

    public Group update(Group group) {
        try {
            group = groupDao.update(group);
        } catch (DaoException e) {
            log.error("Can't update " + group, e);
            throw new ServiceException("Can't update " + group, e);
        }
        return group;
    }

    public void delete(int id) {
        try {
            groupDao.delete(id);
        } catch (DaoException e) {
            log.error("Can't delete group with id " + id, e);
            throw new ServiceException("Can't delete group with id " + id, e);
        }
    }

}
