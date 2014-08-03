package DAO;

import model.Event;

public interface EventDAO extends GenericDAO<Event> {

    public Event getEventData(String name);

}