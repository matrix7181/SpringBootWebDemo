package com.cc.dao.impl;

import com.cc.dao.UserDao;
import com.cc.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by xt on 7/3/17.
 * todo: proper error logging
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public void update(User user) {
        em.merge(user);
    }

    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    /**
     *
     * @param page Starting at 1.
     * @param maxItems Negative value for no limit.
     * @return
     */
    public Collection<User> getUsers(int page, int maxItems) {
        Query query = em.createQuery("SELECT u FROM User u");
        if(maxItems >= 0) {
            query.setMaxResults(maxItems)
                    .setFirstResult((page - 1) * maxItems);
        }
        return (Collection<User>) query.getResultList();
    }

    public void delete(long id) {
        User user = getUserById(id);
        if (user != null) {
            em.remove(user);
        }
    }
}
