package com.cc.dao;

import com.cc.models.User;

import java.util.Collection;

/**
 * Created by xt on 7/3/17.
 */
public interface UserDao {

    void create(User user);

    void update(User user);

    User getUserById(long id);

    Collection<User> getUsers(int page, int maxItems);

    void delete(long id);
}
