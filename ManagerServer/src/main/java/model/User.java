package model;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_events", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id", nullable = false, updatable = false)})
    private Set<Event> events = new HashSet<Event>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_groups", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)})
    private Set<Group> groups = new HashSet<Group>();

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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString(){

        String userData =  "\nUser data: \n" + "ID: " + userID + ", Name: " + userName +
                ", Password: " + password + "\n";

        userData += "Event data: \n";

        for (Event event : events) {
            userData += "ID: " + event.getEventID() + ", Title: " + event.getEventName() +
                    ", Coord.: " + event.getCoordinates() + ", Long.: " + event.getLongitude() +
                    ", Lat.: " + event.getLatitude() + ", Date.: " + event.getDate() + "\n";
        }

        userData += "Group data: \n";

        for (Group group : groups) {
            userData += "ID: " + group.getGroupID() + ", Title: " + group.getGroupName() + "\n";
        }

        return userData;

    }

}