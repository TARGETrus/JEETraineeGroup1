package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    //private static SessionFactory createSessionFactory() {

    static {

        try {

            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate/hibernate.cfg.xml");
            System.out.println("Hibernate Annotation Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable e) {

            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed: " + e.getMessage());
            throw new ExceptionInInitializerError(e);

        }

    }

    //}

    public static SessionFactory getSessionFactory() {

        //if(sessionFactory == null) sessionFactory = buildSessionFactory();

        return sessionFactory;

    }

    public static void closeFactory() {
        sessionFactory.close();
    }

    public static Session beginTransaction() {
        Session hibernateSession = HibernateUtil.getSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void closeSession() {
        HibernateUtil.getSession().close();
    }

}