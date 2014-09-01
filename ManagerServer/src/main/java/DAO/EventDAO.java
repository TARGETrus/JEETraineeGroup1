package DAO;

import model.Event;
import java.util.List;

public interface EventDAO extends GenericDAO<Event> {

    public Event getEventData(String name);

    public Event getCompleteEventData(String name);

    public List<Event> findAll();

    public List<Event> searchEventData(String eventName);

    public List<Event> searchByEventCollectionsData(String userName, String groupName);

    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius);

    public List<Event> getFilteredEventData(Float latitude, Float longitude, Float radius, String userName, String eventName, String groupName);

}