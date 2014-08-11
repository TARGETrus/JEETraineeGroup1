package DAOHandler;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import DAO.HibernateUtil;
import model.Event;
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
            event = (Event) eventDAO.getCompleteEventData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

}