package DAOHandler;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import model.Event;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventDataManager {

    @Autowired
    private EventDAO eventDAO;

    // Create foo
    @Transactional
    public void saveNewEvent(Event event) {

        try {

            eventDAO.save(event);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Get foo
    @Transactional
    public Event getEventData(String name) {

        Event event = null;

        try {

            event = (Event) eventDAO.getEventData(name);;

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    @Transactional
    public Event getEventCompleteData(String name) {

        Event event = null;

        try {

            event = (Event) eventDAO.getCompleteEventData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    @Transactional
    public List<Event> getAllEvents() {

        List<Event> event = null;

        try {

            event = (List<Event>) eventDAO.findAll();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    // Modify foo
    @Transactional
    public void modifyEvent(Event event) {

        try {

            eventDAO.merge(event);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Delete foo
    @Transactional
    public void deleteEvent(Event event) {

        try {

            eventDAO.delete(event);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Filter foo
    @Transactional
    public List<Event> searchEventData(String substr) {

        List<Event> event = null;

        try {

            event = (List<Event>) eventDAO.searchEventData(substr);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    @Transactional
    public List<Event> searchByEventCollectionsData(String userName, String groupName) {

        List<Event> event = null;

        try {

            event = (List<Event>) eventDAO.searchByEventCollectionsData(userName, groupName);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    @Transactional
    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius) {

        List<Event> event = null;

        try {

            event = (List<Event>) eventDAO.getCloseEventData(latitude, longitude, radius);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

    @Transactional
    public List<Event> getFilteredEventData(Float latitude, Float longitude, Float radius, String userName, String eventName, String groupName) {

        List<Event> event = null;

        try {

            event = (List<Event>) eventDAO.getFilteredEventData(latitude, longitude, radius, userName, eventName, groupName);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

}