package DAO;

import model.User;
import org.hibernate.Session;

public class ServerDAOImpl {

    // Users CRUD
    public void insertUser(User user) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {

            System.out.println("Error on insert: " + e.getMessage());

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

    public void removeUser(User user) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();

        } catch (Exception e) {

            System.out.println("Error on delete: " + e.getMessage());

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    public void updateUser(User user) {

        Session session = null;

        try {

            session = HibernateUtil.getSessionAnnotationFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();

        } catch (Exception e) {

            System.out.println("Error on update: " + e.getMessage());

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    // Events CRUD
    public void insertEvent() {

    }

    public void selectEvent() {

    }

    public void removeEvent() {

    }

    public void updateEvent() {

    }

    // Groups CRUD
    public void insertGroup() {

    }

    public void selectGrouo() {

    }

    public void removeGroup() {

    }

    public void updateGroup() {

    }

    // Comments CRUD
    public void insertComment() {

    }

    public void selectComment() {

    }

    public void removeComment() {

    }

    public void updateComment() {

    }

}
