package DAO;

import model.Event;
import org.hibernate.Query;
import org.hibernate.Session;

public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    public Event getEventData(String name) {
        Session hibernateSession = this.getSession();
        Query query = hibernateSession.createQuery("from Event where event_name= :name");
        query.setString("name", name);
        return findOne(query);
    }

}