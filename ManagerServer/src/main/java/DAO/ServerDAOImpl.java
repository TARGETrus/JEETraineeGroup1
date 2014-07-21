package DAO;

import model.User;
import org.hibernate.Session;

public class ServerDAOImpl {

    // Users CRUD
    public long insertUser(User user) {

        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        session.beginTransaction();
        Integer result = (Integer) session.save(user);
        session.getTransaction().commit();

        return result;

    }

    public User selectUser(User user) {

        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        session.beginTransaction();
        User result = (User) session.load(User.class, user.getUserID());
        session.getTransaction().commit();

        return result;

    }

    public void removeUser(User user) {

    }

    public void updateUser(User user) {

        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();

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
