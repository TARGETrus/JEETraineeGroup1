package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public User getUserData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from User as user " +
                "where user.userName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public User getCompleteUserData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from User as user " +
                "left join fetch user.events as events " +
                "left join fetch events.users " +
                "left join fetch events.groups " +
                "left join fetch events.comments " +
                "left join fetch user.groups as groups " +
                "left join fetch groups.users " +
                "left join fetch groups.events " +
                "left join fetch user.filters as filters " +
                "where user.userName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}