package model;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="event_id", nullable=false, unique=true, length=12)
    private int    eventID;

    @Column(name="event_name", length=255, nullable=false)
    private String eventName;

    @Column(name="coordinates", length=255, nullable=false)
    private String coordinates;

    @Column(name="date", length=255, nullable=false)
    private String date;

    @Column(name="user_name", length=255, nullable=false)
    private String userName;

    public  Event() {}

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}