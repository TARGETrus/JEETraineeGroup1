package model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="groups", schema="web_app_db")
public class Groupp {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="group_id", length=12, nullable=false, unique=true)
    private int    groupID;

    @Column(name="group_name", length=255, nullable=false, unique=true)
    private String groupName;

    @Column(name="group_admin", length=255, nullable=false)
    private String groupAdmin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_groups", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private Set<User> users = new HashSet<User>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events_groups", schema="web_app_db",
            joinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "event_id", nullable = false, updatable = false)})
    private Set<Event> events = new HashSet<Event>();

    public Groupp() {}

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(String groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public String toString(){

        String groupData =  "\nGroup data: \n" + "ID: " + groupID + ", Title: " + groupName +
                ", Admin.: " + groupAdmin + "\n";

        groupData += "User data: \n";

        if (Hibernate.isInitialized(users)) {

            for (User user : users) {
                groupData += "ID: " + user.getUserID() + ", Name: " + user.getUserName() +
                        ", Password: " + user.getPassword() + ", Role: " + user.getRole() + "\n";
            }

        }

        groupData += "Event data: \n";

        if (Hibernate.isInitialized(users)) {

            for (Event event : events) {
                groupData += "ID: " + event.getEventID() + ", Title: " + event.getEventName() +
                        ", Coord.: " + event.getCoordinates() + ", Long.: " + event.getLongitude() +
                        ", Lat.: " + event.getLatitude() + ", Date.: " + event.getDate() +
                        ", Event Admin.: " + event.getEventAdmin() + "\n";
            }

        }

        return groupData;

    }

}