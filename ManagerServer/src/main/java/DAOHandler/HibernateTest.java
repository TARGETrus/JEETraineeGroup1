package DAOHandler;

import model.*;
import java.util.List;

public class HibernateTest {
/*
    public static void main(String[] args) {

        UserDataManager    userDataManager    = new UserDataManager();
        GroupDataManager   groupDataManager   = new GroupDataManager();
        EventDataManager   eventDataManager   = new EventDataManager();
        FilterDataManager  filterDataManager  = new FilterDataManager();
        CommentDataManager commentDataManager = new CommentDataManager();

        Groupp group = new Groupp();
        group.setGroupName("group");
        group.setGroupAdmin("name");

        Event event = new Event();
        event.setEventName("event");
        event.setDate("1.10.2014");
        event.setCoordinates("1444:2111");
        event.setLatitude(100F);
        event.setLongitude(100F);
        event.setEventAdmin("name");

        User user = new User();
        //testUser.setUserID(2);
        user.setUserName("name");
        user.setPassword("password");
        user.setRole("admin");

        event.getUsers().add(user);
        group.getUsers().add(user);
        group.getEvents().add(event);

        Filter filter = new Filter();
        filter.setFilterName("filter");
        filter.setFilterData("asd:asd dsa:dsa");
        filter.setUser(user);

        Comment comment = new Comment();
        comment.setCommentName("comment");
        comment.setComment("comment description");
        comment.setEvent(event);

        Groupp group1 = new Groupp();
        group1.setGroupName("group1");
        group1.setGroupAdmin("name1");

        Event event1 = new Event();
        event1.setEventName("event1");
        event1.setDate("1.10.2014");
        event1.setCoordinates("1444:2111");
        event1.setLatitude(1100F);
        event1.setLongitude(1100F);
        event1.setEventAdmin("name1");

        User user1 = new User();
        //testUser.setUserID(2);
        user1.setUserName("name1");
        user1.setPassword("password");
        user1.setRole("regular");

        event1.getGroups().add(group1);
        user1.getGroups().add(group1);
        user1.getEvents().add(event1);

        Filter filter1 = new Filter();
        filter1.setFilterName("filter1");
        filter1.setFilterData("asd:asd dsa:dsa");
        filter1.setUser(user1);

        Comment comment1 = new Comment();
        comment1.setCommentName("comment1");
        comment1.setComment("comment description");
        comment1.setEvent(event1);

        //userDataManager.saveNewUser(user);
        //eventDataManager.saveNewEvent(event);
        //groupDataManager.saveNewGroup(group);
        //filterDataManager.saveNewFilter(filter);
        //commentDataManager.saveNewComment(comment);

        //groupDataManager.saveNewGroup(group1);
        //eventDataManager.saveNewEvent(event1);
        //userDataManager.saveNewUser(user1);
        //filterDataManager.saveNewFilter(filter1);
        //commentDataManager.saveNewComment(comment1);

        //User gotUser = userDataManager.getUserCompleteData("name");
        //userDataManager.deleteUser(gotUser);
        //gotUser.getEvents().add(event);
        //userDataManager.modifyUser(gotUser);

        //Event gotEvent = eventDataManager.getEventCompleteData("event1");
        //eventDataManager.deleteEvent(gotEvent);

        //Groupp gotGroup = groupDataManager.getGroupCompleteData("group1");
        //groupDataManager.deleteGroup(gotGroup);

        //userDataManager.changeUserPassword("name", "password");


        User userCompleteData = userDataManager.getUserCompleteData("name");
        System.out.println(userCompleteData.toString());
        Set<Event> events = userCompleteData.getEvents();

        for (Event eve : events) {

            System.out.println(eve.getGroups().toString());

        }

        List<Event> searchEvent = eventDataManager.searchEventData("event");

        if (searchEvent != null) {
            System.out.println("event " + searchEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        List<Event> collEvent = eventDataManager.searchByEventCollectionsData("name", "group");

        if (collEvent != null) {
            System.out.println("Collect " + collEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        List<Event> closeEvent = eventDataManager.getCloseEventData(0F, 0F, 1500F);

        if (closeEvent != null) {
            System.out.println("close " + closeEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }


        List<Event> filtEvent = eventDataManager.getFilteredEventData(100.12F, 100.12F, 5.24F, "name", "event", "group");

        if (filtEvent != null) {
            System.out.println("filter " + filtEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        //Groupp groups = groupDataManager.getGroupCompleteData("group");
        //System.out.println(groups);

        HibernateUtil.closeFactory();

    }
*/
}