package model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users", schema="web_app_db")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id", length=12, nullable=false, unique=true)
    private int    userID;

    @Column(name="user_name", length=255, nullable=false, unique=true)
    private String userName;

    @Column(name="password", length=255, nullable=false)
    private String password;

    @Column(name="role", length=255, nullable=false)
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_events", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id", nullable = false, updatable = false)})
    private Set<Event> events = new HashSet<Event>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_groups", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)})
    private Set<Groupp> groups = new HashSet<Groupp>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Filter> filters = new HashSet<Filter>();

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Groupp> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groupp> groups) {
        this.groups = groups;
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public String toString(){

        String userData =  "\nUser data: \n" + "ID: " + userID + ", Name: " + userName +
                ", Password: " + password + ", Role: " + role + "\n";

        userData += "Event data: \n";

        if (Hibernate.isInitialized(events)) {

            for (Event event : events) {
                userData += "ID: " + event.getEventID() + ", Title: " + event.getEventName() +
                        ", Coord.: " + event.getCoordinates() + ", Long.: " + event.getLongitude() +
                        ", Lat.: " + event.getLatitude() + ", Date.: " + event.getDate() + "\n";
            }

        }

        userData += "Groupp data: \n";

        if (Hibernate.isInitialized(groups)) {

            for (Groupp group : groups) {
                userData += "ID: " + group.getGroupID() + ", Title: " + group.getGroupName() + "\n";
            }

        }

        userData += "Filter data: \n";

        if (Hibernate.isInitialized(filters)) {

            for (Filter filter : filters) {
                userData += "ID: " + filter.getFilterID() + ", Title: " + filter.getFilterName() + "\n";
            }

        }

        return userData;

    }


}