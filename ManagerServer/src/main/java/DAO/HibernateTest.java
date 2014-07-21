package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {

    public static void main(String[] args) {

        // Create something to add
        User user = new User();
        user.setUserName("Test1");
        user.setPassword("pass1");

        // Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        // Start transaction
        session.beginTransaction();

        // Save the Model object
        session.save(user);

        // Commit transaction
        session.getTransaction().commit();
        System.out.println("User ID="+user.getUserID());

        // Terminate session factory
        sessionFactory.close();

    }

}