package aDAOTest;

import DAO.*;
import model.Comment;
import model.User;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class TestManager {

    private UserDAO userDAO = new UserDAOImpl();
    private CommentDAO commentDAO = new CommentDAOImpl();

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

    public void mergeUser(User user) {

        try {

            HibernateUtil.beginTransaction();
            userDAO.merge(user);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void deleteUser(User user) {

        try {

            HibernateUtil.beginTransaction();
            userDAO.delete(user);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public User getUserLoginData(String name) {

        User person = null;

        try {

            HibernateUtil.beginTransaction();
            person = (User) userDAO.getLoginData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return person;

    }

    public User findUserById(int id) {

        User person = null;

        try {

            HibernateUtil.beginTransaction();
            person = (User) userDAO.findByID(User.class, id);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return person;

    }

    public List<User> findAllUsers() {

        List<User> allPersons = new ArrayList<User>();

        try {

            HibernateUtil.beginTransaction();
            allPersons = userDAO.findAll(User.class);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return allPersons;

    }

    public void saveNewComment(Comment comment) {

        try {

            HibernateUtil.beginTransaction();
            commentDAO.save(comment);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

}