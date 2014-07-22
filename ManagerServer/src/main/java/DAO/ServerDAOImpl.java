package DAO;

import model.Comment;
import model.Event;
import model.Group;
import model.User;
import org.hibernate.Session;

public class ServerDAOImpl implements ServerDAO {

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

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.save(event);
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

    public Event selectEvent(int id) {

        Session session = null;
        Event event = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            event = (Event) session.load(Event.class, id);

        } catch (Exception e) {

            System.out.println("Error on select: " + e.getMessage());

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return event;

    }

    public boolean removeEvent(Event event) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.delete(event);
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

    public boolean updateEvent(Event event) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.update(event);
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

    // Groups CRUD
    public boolean insertGroup(Group group) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.save(group);
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

    public Group selectGrouo(int id) {

        Session session = null;
        Group group = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            group = (Group) session.load(Group.class, id);

        } catch (Exception e) {

            System.out.println("Error on select: " + e.getMessage());

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return group;

    }

    public boolean removeGroup(Group group) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.delete(group);
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

    public boolean updateGroup(Group group) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.update(group);
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

    // Comments CRUD
    public boolean insertComment(Comment comment) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.save(comment);
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

    public Comment selectComment(int id) {

        Session session = null;
        Comment comment = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            comment = (Comment) session.load(Comment.class, id);

        } catch (Exception e) {

            System.out.println("Error on select: " + e.getMessage());

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return comment;

    }

    public boolean removeComment(Comment comment) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.delete(comment);
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

    public boolean updateComment(Comment comment) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.update(comment);
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

}