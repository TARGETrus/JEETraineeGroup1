package DAO;

import model.Event;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    public Event getEventData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Event as event " +
                "where event.eventName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public Event getCompleteEventData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where event.eventName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public List<Event> searchEventData(String eventName) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Event as event " +
                "where event.eventName like :eventName");
        query.setString("eventName", '%' + eventName + '%');

        return findMany(query);

    }

    public List<Event> searchByEventCollectionsData(String userName, String groupName) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where users.userName = :userName or groups.groupName = :groupName");
        query.setString("userName", userName);
        query.setString("groupName", groupName);

        return findMany(query);

    }

    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Event as event " +
                "where (pow((event.latitude - :latitude), 2) + pow((event.longitude - :longitude), 2)) <= pow(:radius, 2)");
        query.setFloat("latitude", Math.abs(latitude));
        query.setFloat("longitude", Math.abs(longitude));
        query.setFloat("radius", Math.abs(radius));

        return findMany(query);

    }

    public List<Event> getFilteredEventData(Float latitude, Float longitude, Float radius, String userName, String eventName, String groupName) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where (event.eventName like :eventName) " +
                "and (users.userName = :userName or groups.groupName = :groupName) " +
                "and (pow((event.latitude - :latitude), 2) + pow((event.longitude - :longitude), 2)) <= pow(:radius, 2)");
        query.setString("eventName", '%' + eventName + '%');
        query.setString("userName", userName);
        query.setString("groupName", groupName);
        query.setFloat("latitude", Math.abs(latitude));
        query.setFloat("longitude", Math.abs(longitude));
        query.setFloat("radius", Math.abs(radius));

        return findMany(query);

    }

}