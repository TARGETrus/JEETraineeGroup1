package DAOHandler;

import DAO.*;
import model.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDataManager {

    @Autowired
    private UserDAO userDAO;

    // Create foo
    @Transactional
    public void saveNewUser(User user) {

        try {

            userDAO.save(user);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Get foo
    @Transactional
    public User getUserData(String name) {

        User user = null;

        try {

            user = (User) userDAO.getUserData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    @Transactional
    public User getUserCompleteData(String name) {

        User user = null;

        try {

            user = (User) userDAO.getCompleteUserData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    @Transactional
    public List<User> getAllUsers() {

        List<User> user = null;

        try {

            user = (List<User>) userDAO.findAll(User.class);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    @Transactional
    public User findUserById(int id) {

        User user = null;

        try {

            user = (User) userDAO.findByID(User.class, id);
            Hibernate.initialize(user.getEvents());
            Hibernate.initialize(user.getGroups());

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return user;

    }

    // Modify foo
    @Transactional
    public void modifyUser(User user) {

        try {

            userDAO.merge(user);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    @Transactional
    public void changeUserPassword(String userName, String password) {

        User user = null;

        try {

            user = userDAO.getUserData(userName);
            user.setPassword(password);
            userDAO.merge(user);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    @Transactional
    public void changeUserName(String userName, String name) {

        User user = null;

        try {

            user = userDAO.getUserData(userName);
            user.setUserName(name);
            userDAO.merge(user);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Delete foo
    @Transactional
    public void deleteUser(User user) {

        try {

            userDAO.delete(user);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

}