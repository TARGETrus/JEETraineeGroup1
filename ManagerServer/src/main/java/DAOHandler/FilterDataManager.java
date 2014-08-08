package DAOHandler;

import DAO.EventDAO;
import DAO.EventDAOImpl;
import DAO.HibernateUtil;
import model.Event;
import org.hibernate.HibernateException;

import java.util.List;

public class FilterDataManager {

    private EventDAO eventDAO = new EventDAOImpl();

    // Get foo
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

    public List<Event> getCloseEventData(int latitude, int longitude) {

        List<Event> event = null;

        try {

            HibernateUtil.beginTransaction();
            event = (List<Event>) eventDAO.getCloseEventData(latitude, longitude);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return event;

    }

}
