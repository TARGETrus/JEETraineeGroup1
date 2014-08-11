package model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="groups", schema="web_app_db")
public class Groupp {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="group_id", nullable=false, unique=true, length=12)
    private int    groupID;

    @Column(name="group_name", length=255, nullable=false)
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupps")
    private Set<User> users = new HashSet<User>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupps")
    private Set<Event> events = new HashSet<Event>();

    public Groupp() {}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
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

        String groupData =  "\nGroupp data: \n" + "ID: " + groupID + ", Title: " + groupName + "\n";

        groupData += "User data: \n";

        if (Hibernate.isInitialized(users)) {

            for (User user : users) {
                groupData += "ID: " + user.getUserID() + ", Name: " + user.getUserName() +
                        ", Password: " + user.getPassword() + "\n";
            }

        }

        groupData += "Event data: \n";

        if (Hibernate.isInitialized(users)) {

            for (Event event : events) {
                groupData += "ID: " + event.getEventID() + ", Title: " + event.getEventName() +
                        ", Coord.: " + event.getCoordinates() + ", Long.: " + event.getLongitude() +
                        ", Lat.: " + event.getLatitude() + ", Date.: " + event.getDate() + "\n";
            }

        }

        return groupData;

    }

}