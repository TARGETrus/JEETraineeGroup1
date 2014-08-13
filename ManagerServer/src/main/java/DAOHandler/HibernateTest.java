package DAOHandler;

import DAO.HibernateUtil;
import model.Comment;
import model.Event;
import model.Groupp;
import model.User;

import java.util.List;
import java.util.Set;

public class HibernateTest {

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
        event.getGroups().add(group);

        User testUser = new User();
        //testUser.setUserID(2);
        testUser.setUserName("name");
        testUser.setPassword("password");
        testUser.setRole("admin");
        testUser.getEvents().add(event);
        testUser.getGroups().add(group);

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
        event1.getGroups().add(group1);

        User testUser1 = new User();
        //testUser.setUserID(2);
        testUser1.setUserName("name1");
        testUser1.setPassword("password");
        testUser1.setRole("regular");
        testUser1.getEvents().add(event1);
        testUser1.getGroups().add(group1);

        Comment comment = new Comment();
        comment.setCommentName("comment");
        comment.setComment("comment description");
        comment.setEvent(event);

        Comment comment1 = new Comment();
        comment1.setCommentName("comment1");
        comment1.setComment("comment description");
        comment1.setEvent(event1);

        //userDataManager.saveNewUser(testUser);
        //commentDataManager.saveNewComment(comment);
        //userDataManager.saveNewUser(testUser1);
        //commentDataManager.saveNewComment(comment1);

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

        List<Event> filtEvent = eventDataManager.getFilteredEventData(0F, 0F, null, null, "event", null);

        if (filtEvent != null) {
            System.out.println("filter " + filtEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        //Groupp groups = groupDataManager.getGroupCompleteData("group");
        //System.out.println(groups);

        HibernateUtil.closeFactory();

    }

}