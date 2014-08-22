package DAO;

import model.Event;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    public Event getEventData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Event as event " +
                "where event.eventName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public Event getCompleteEventData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where event.eventName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public List<Event> findAll() {

        List<Event> all = null;
        Query query = sessionFactory.getCurrentSession().createQuery("from Event as event " +
                "left join fetch event.users as users");
        all = (List<Event>) query.list();

        return all;

    }

    public List<Event> searchEventData(String eventName) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Event as event " +
                "where event.eventName like :eventName");
        query.setString("eventName", '%' + eventName + '%');

        return findMany(query);

    }

    public List<Event> searchByEventCollectionsData(String userName, String groupName) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where users.userName = :userName or groups.groupName = :groupName");
        query.setString("userName", userName);
        query.setString("groupName", groupName);

        return findMany(query);

    }

    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Event as event " +
                "where (pow((event.latitude - :latitude), 2) + pow((event.longitude - :longitude), 2)) <= pow(:radius, 2)");
        query.setFloat("latitude", Math.abs(latitude));
        query.setFloat("longitude", Math.abs(longitude));
        query.setFloat("radius", Math.abs(radius));

        return findMany(query);

    }

    public List<Event> getFilteredEventData(Float latitude, Float longitude, Float radius, String userName, String eventName, String groupName) {

        if (latitude != null)  latitude  = Math.abs(latitude);
        if (longitude != null) longitude = Math.abs(longitude);
        if (radius != null)    radius    = Math.abs(radius);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("eventName", '%' + eventName + '%');
        params.put("userName", userName);
        params.put("groupName", groupName);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("radius", radius);

        StringBuilder queryString = new StringBuilder();

        queryString.append("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where 1 = 1 ");

        if (eventName != null) {
            queryString.append("and (event.eventName like :eventName) ");
        }

        if (userName != null || groupName != null) {
            queryString.append("and (users.userName = :userName or groups.groupName = :groupName) ");
        }

        if (latitude != null && longitude != null && radius != null) {
            queryString.append("and (pow((event.latitude - :latitude), 2) + pow((event.longitude - :longitude), 2)) <= pow(:radius, 2)");
        }

        Query query = sessionFactory.getCurrentSession().createQuery(queryString.toString());

        for (String statement : query.getNamedParameters()) {
            query.setParameter(statement, params.get(statement));
        }

        return findMany(query);

    }

}