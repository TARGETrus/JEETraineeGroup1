package DAOHandler;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import DAO.HibernateUtil;
import model.Event;
import org.hibernate.HibernateException;

import java.util.List;

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
    public Event getEventData(String name) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (Event) eventDAO.getEventData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    public Event getEventCompleteData(String name) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (Event) eventDAO.getCompleteEventData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    public List<Event> getAllEvents() {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.findAll(Event.class);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    // Modify foo
    public void modifyEvent(Event event) {

        try {

            HibernateUtil.beginTransaction();
            eventDAO.merge(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Filter foo
    public List<Event> searchEventData(String substr) {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.searchEventData(substr);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    public List<Event> searchByEventCollectionsData(String userName, String groupName) {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.searchByEventCollectionsData(userName, groupName);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius) {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.getCloseEventData(latitude, longitude, radius);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    public List<Event> getFilteredEventData(Float latitude, Float longitude, Float radius, String userName, String eventName, String groupName) {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.getFilteredEventData(latitude, longitude, radius, userName, eventName, groupName);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

}