package model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="events", schema="web_app_db")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="event_id", length=12, nullable=false, unique=true)
    private int    eventID;

    @Column(name="event_name", length=255, nullable=false, unique=true)
    private String eventName;

    @Column(name="coordinates", length=255)
    private String coordinates;

    @Column(name="latitude", length=255, nullable=false)
    private Float latitude;

    @Column(name="longitude", length=255, nullable=false)
    private Float longitude;

    @Column(name="date", length=255, nullable=false)
    private String date;

    @Column(name="event_admin", length=255, nullable=false)
    private String eventAdmin;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "events")
    private Set<User> users = new HashSet<User>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "events_groups", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "event_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)})
    private Set<Groupp> groups = new HashSet<Groupp>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Comment> comments = new HashSet<Comment>();

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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventAdmin() {
        return eventAdmin;
    }

    public void setEventAdmin(String eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Groupp> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groupp> groups) {
        this.groups = groups;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString(){

        String eventData =  "\nEvent data: \n" + "ID: " + eventID + ", Title: " + eventName +
                ", Coord.: " + coordinates + ", Long.: " + longitude +
                ", Lat.: " + latitude + ", Date.: " + date + ", Admin.: " + eventAdmin + "\n";

        eventData += "User data: \n";

        if (Hibernate.isInitialized(users)) {

            for (User user : users) {
                eventData += "ID: " + user.getUserID() + ", Name: " + user.getUserName() +
                        ", Password: " + user.getPassword() + "\n";
            }

        }

        eventData += "Groupp data: \n";

        if (Hibernate.isInitialized(groups)) {

            for (Groupp group : groups) {
                eventData += "ID: " + group.getGroupID() + ", Title: " + group.getGroupName() + "\n";
            }

        }

        eventData += "Comment data: \n";

        if (Hibernate.isInitialized(comments)) {

            for (Comment comment : comments) {
                eventData += "ID: " + comment.getCommentID() + ", Title: " + comment.getCommentName() +
                        ", Comment: " + comment.getComment() + "\n";
            }

        }

        return eventData;

    }

}