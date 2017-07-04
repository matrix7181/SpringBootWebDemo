package com.cc.service;

import com.cc.dao.UserDao;
import com.cc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by xt on 7/3/17.
 *
 * todo: proper error handling / logging
 */
@Service
@Transactional
public class ApiUserRequestService {

    @Autowired
    private UserDao userDao;

    public Collection<User> fetch(int page, int maxPerPage) {
        return userDao.getUsers(page, maxPerPage);
    }

    public boolean create(User u) {
        try {
            userDao.create(u);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public User fetchById(long id) {
        return userDao.getUserById(id);
    }

    public boolean deleteById(long id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
