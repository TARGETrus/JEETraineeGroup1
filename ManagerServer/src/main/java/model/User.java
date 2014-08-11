package model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users", schema="web_app_db")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false, unique=true, length=12)
    private int    userID;

    @Column(name="user_name", length=255, nullable=false, unique=true)
    private String userName;

    @Column(name="password", length=255, nullable=false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_events", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id", nullable = false, updatable = false)})
    private Set<Event> events = new HashSet<Event>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_groups", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)})
    private Set<Groupp> groupps = new HashSet<Groupp>();

    public User() {}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Groupp> getGroupps() {
        return groupps;
    }

    public void setGroupps(Set<Groupp> groupps) {
        this.groupps = groupps;
    }

    @Override
    public String toString(){

        String userData =  "\nUser data: \n" + "ID: " + userID + ", Name: " + userName +
                ", Password: " + password + "\n";

        userData += "Event data: \n";

        if (Hibernate.isInitialized(events)) {

            for (Event event : events) {
                userData += "ID: " + event.getEventID() + ", Title: " + event.getEventName() +
                        ", Coord.: " + event.getCoordinates() + ", Long.: " + event.getLongitude() +
                        ", Lat.: " + event.getLatitude() + ", Date.: " + event.getDate() + "\n";
            }

        }

        userData += "Groupp data: \n";

        if (Hibernate.isInitialized(groupps)) {

            for (Groupp groupp : groupps) {
                userData += "ID: " + groupp.getGroupID() + ", Title: " + groupp.getGroupName() + "\n";
            }

        }

        return userData;

    }


}