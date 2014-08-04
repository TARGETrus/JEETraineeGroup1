package DAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {

    // Call the static initializer
    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory();
    }

    // Free all resources
    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.closeFactory();
    }

}