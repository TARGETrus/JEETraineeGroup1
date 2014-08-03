package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public User getUserData(String name) {
        Session hibernateSession = this.getSession();
        Query query = hibernateSession.createQuery("from User where user_name= :name");
        query.setString("name", name);
        return findOne(query);
    }

}