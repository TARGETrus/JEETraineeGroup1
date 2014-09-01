package DAO;

import model.User;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public User getUserData(String name) {

        Query query = entityManager.createQuery("from User as user " +
                "where user.userName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

    public User getCompleteUserData(String name) {

        Query query = entityManager.createQuery("from User as user " +
                "left join fetch user.events as events " +
                "left join fetch events.users " +
                "left join fetch events.groups " +
                "left join fetch events.comments " +
                "left join fetch user.groups as groups " +
                "left join fetch groups.users " +
                "left join fetch groups.events " +
                "left join fetch user.filters as filters " +
                "where user.userName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

}