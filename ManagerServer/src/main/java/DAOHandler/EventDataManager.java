package DAOHandler;

import DAO.*;
import model.Event;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

public class EventDataManager {

    private EventDAO eventDAO = new EventDAOImpl();

    // Create foo
    public void saveNewEvent(Event event) {

        try {

            HibernateUtil.beginTransaction();
            eventDAO.save(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Get foo
    public Event getEventCompleteData(String name) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (Event) eventDAO.getEventData(name);
            Hibernate.initialize(event.getUsers());
            Hibernate.initialize(event.getGroups());
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

}