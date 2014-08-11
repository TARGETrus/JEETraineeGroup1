package DAOHandler;

import DAO.HibernateUtil;
import model.Comment;
import model.Event;
import model.Group;
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

        Group group = new Group();
        group.setGroupName("group");

        Event event = new Event();
        event.setEventName("event");
        event.setDate("1.10.2014");
        event.setCoordinates("1444:2111");
        event.setLatitude(100F);
        event.setLongitude(100F);
        event.getGroups().add(group);

        User testUser = new User();
        //testUser.setUserID(2);
        testUser.setUserName("name");
        testUser.setPassword("password");
        testUser.getEvents().add(event);
        testUser.getGroups().add(group);

        Comment comment = new Comment();
        comment.setCommentName("comment");
        comment.setComment("comment description");
        comment.setEvent(event);

        //userDataManager.saveNewUser(testUser);
        //commentDataManager.saveNewComment(comment);

        //userDataManager.changeUserPassword("name", "password");

        User userCompleteData = userDataManager.getUserCompleteData("name");
        //System.out.println(userCompleteData.toString());
        Set<Event> events = userCompleteData.getEvents();

        for (Event eve : events) {

            //System.out.println(eve.getGroups().toString());

        }

        List<Event> searchEvent = filterDataManager.searchEventData("event");

        if (searchEvent != null) {
            //System.out.println("event " + searchEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        List<Event> collEvent = filterDataManager.searchByEventCollectionsData("name1", "group");

        if (collEvent != null) {
            //System.out.println("Collect " + collEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        List<Event> closeEvent = filterDataManager.getCloseEventData(0F, 1000F, 1000F);

        if (closeEvent != null) {
            System.out.println("close " + closeEvent.toString());
        } else {
            System.out.println("Nothing found!!!");
        }

        //Group groups = groupDataManager.getGroupCompleteData("group");
        //System.out.println(groups);

        HibernateUtil.closeFactory();

    }

}