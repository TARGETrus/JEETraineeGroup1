package DAO;

import model.Comment;
import model.Event;
import model.Group;
import model.User;
import org.hibernate.Session;

public class ServerDAOImpl implements ServerDAO{

    // Users CRUD
    public boolean insertUser(User user) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {

            System.out.println("Error on insert: " + e.getMessage());
            return false;

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    public User selectUser(int id) {

        Session session = null;
        User user = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            user = (User) session.load(User.class, id);

        } catch (Exception e) {

            System.out.println("Error on select: " + e.getMessage());

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return user;

    }

    public boolean removeUser(User user) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {

            System.out.println("Error on delete: " + e.getMessage());
            return false;

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    public boolean updateUser(User user) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {

            System.out.println("Error on update: " + e.getMessage());
            return false;

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    // Events CRUD
    public boolean insertEvent(Event event) {
        return false;
    }

    public Event selectEvent(int eventID) {
        return null;
    }

    public boolean removeEvent(Event event) {
        return false;
    }

    public boolean updateEvent(Event event) {
        return false;
    }

    public boolean insertGroup(Group group) {
        return false;
    }

    // Groups CRUD
    public Group selectGrouo(int groupID) {
        return null;
    }

    public boolean removeGroup(Group group) {
        return false;
    }

    public boolean updateGroup(Group group) {
        return false;
    }

    // Comments CRUD
    public boolean insertComment(Comment comment) {
        return false;
    }

    public Comment selectComment(int commentID) {
        return null;
    }

    public boolean removeComment(Comment comment) {
        return false;
    }

    public boolean updateComment(Comment comment) {
        return false;
    }









}
