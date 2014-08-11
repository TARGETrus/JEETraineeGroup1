package DAOHandler;

import DAO.*;
import model.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

public class UserDataManager {

    private UserDAO userDAO = new UserDAOImpl();

    // Create foo
    public void saveNewUser(User user) {

        try {

            HibernateUtil.beginTransaction();
            userDAO.save(user);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Get foo
    public User getUserData(String name) {

        User user = null;

        try {

            HibernateUtil.beginTransaction();
            user = (User) userDAO.getUserData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    public User getUserCompleteData(String name) {

        User user = null;

        try {

            HibernateUtil.beginTransaction();
            user = (User) userDAO.getCompleteUserData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    public User findUserById(int id) {

        User user = null;

        try {

            HibernateUtil.beginTransaction();
            user = (User) userDAO.findByID(User.class, id);
            Hibernate.initialize(user.getEvents());
            Hibernate.initialize(user.getGroupps());
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    // Modify foo
    public void changeUserPassword(String userName, String password) {

        User user = null;

        try {

            HibernateUtil.beginTransaction();
            user = userDAO.getUserData(userName);

            user.setPassword(password);

            userDAO.merge(user);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeUserName(String userName, String name) {

        User user = null;

        try {

            HibernateUtil.beginTransaction();
            user = userDAO.getUserData(userName);

            user.setUserName(name);

            userDAO.merge(user);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

}