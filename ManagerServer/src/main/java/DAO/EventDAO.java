package DAO;

import model.Event;

import java.util.List;

public interface EventDAO extends GenericDAO<Event> {

    public Event getEventData(String name);

    public Event getCompleteEventData(String name);

    public List<Event> searchEventData(String substr);

    public List<Event> searchByEventCollectionsData(String userName, String groupName);

    public List<Event> getCloseEventData(Float latitude, Float longitude, Float radius);

}