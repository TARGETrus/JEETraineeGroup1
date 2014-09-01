package DAO;

import model.Event;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Parameter;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {

    public Event getEventData(String name) {

        Query query = entityManager.createQuery("from Event as event " +
                "where event.eventName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

    public Event getCompleteEventData(String name) {

        Query query = entityManager.createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where event.eventName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

    @SuppressWarnings("unchecked")
    public List<Event> findAll() {
        Query query = entityManager.createQuery("from Event as event " +
                "left join fetch event.users as users");
        return (List<Event>) query.getResultList();
    }

    public List<Event> searchEventData(String eventName) {

        Query query = entityManager.createQuery("from Event as event " +
                "where event.eventName like :eventName");
        query.setParameter("eventName", '%' + eventName + '%');

        return findMany(query);

    }

    public List<Event> searchByEventCollectionsData(String userName, String groupName) {

        Query query = entityManager.createQuery("from Event as event " +
                "left join fetch event.groups as groups " +
                "left join fetch event.users as users " +
                "left join fetch event.comments as comments " +
                "where users.userName = :userName or groups.groupName = :groupName");
        query.setParameter("userName", userName);
        query.setParameter("groupName", groupName);

        return findMany(query);

    }

    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius) {

        Query query = entityManager.createQuery("from Event as event " +
                "where (pow((event.latitude - :latitude), 2) + pow((event.longitude - :longitude), 2)) <= pow(:radius, 2)");
        query.setParameter("latitude", Math.abs(latitude));
        query.setParameter("longitude", Math.abs(longitude));
        query.setParameter("radius", Math.abs(radius));

        return findMany(query);

    }

    public List<Event> getFilteredEventData(Float latitude, Float longitude, Float radius, String userName, String eventName, String groupName) {

        if (latitude != 0)  latitude  = Math.abs(latitude);
        if (longitude != 0) longitude = Math.abs(longitude);
        if (radius != 0)    radius    = Math.abs(radius);

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

        if (latitude != 0 && longitude != 0 && radius != 0) {
            queryString.append("and (pow((event.latitude - :latitude), 2) + pow((event.longitude - :longitude), 2)) <= pow(:radius, 2)");
        }

        Query query = entityManager.createQuery(queryString.toString());

        for (Parameter<?> statement : query.getParameters()) {
            query.setParameter(statement.getName(), params.get(statement));
        }

        return findMany(query);

    }

}