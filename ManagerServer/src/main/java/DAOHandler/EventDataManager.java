package DAOHandler;

import DAO.*;
import model.Comment;
import model.Event;
import model.User;
import org.hibernate.HibernateException;

import java.util.List;

public class EventDataManager {

    private EventDAO   eventDAO   = new EventDAOImpl();
    private CommentDAO commentDAO = new CommentDAOImpl();

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

    public List<Event> getAllEventsJoin() {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.findAll();
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
            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeEventName(String eventName, String name) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = eventDAO.getEventData(eventName);

            event.setEventName(name);

            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeEventCoordinates(String eventName, String coordinates) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = eventDAO.getEventData(eventName);

            event.setCoordinates(coordinates);

            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeEventLatitude(String eventName, Float latitude) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = eventDAO.getEventData(eventName);

            event.setLatitude(latitude);

            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeEventLongitude(String eventName, Float longitude) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = eventDAO.getEventData(eventName);

            event.setLongitude(longitude);

            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeEventDate(String eventName, String date) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = eventDAO.getEventData(eventName);

            event.setDate(date);

            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    public void changeEventAdmin(String eventName, String admin) {

        Event event = null;

        try {

            HibernateUtil.beginTransaction();
            event = eventDAO.getEventData(eventName);

            event.setEventAdmin(admin);

            eventDAO.update(event);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Delete foo
    public boolean deleteEvent(Event event) {

        try {

            HibernateUtil.beginTransaction();

            event = (Event) eventDAO.getCompleteEventData(event.getEventName());

            eventDAO.delete(event);

            HibernateUtil.commitTransaction();

            return true;

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

            return false;

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