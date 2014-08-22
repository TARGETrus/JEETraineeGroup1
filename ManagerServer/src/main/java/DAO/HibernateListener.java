package DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Service
public class HibernateListener implements ServletContextListener {

    @Autowired
    private SessionFactory sessionFactory;

    // Call the static initializer
    public void contextInitialized(ServletContextEvent event) {
        sessionFactory.getCurrentSession();
    }

    // Free all resources
    public void contextDestroyed(ServletContextEvent event) {

    }

}