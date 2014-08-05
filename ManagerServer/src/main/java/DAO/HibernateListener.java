package DAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
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