import DAOHandler.*;
import model.*;
import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertEquals;

public class TestUserDataManager {
/*
    private User    user    = new User();
    private Event   event   = new Event();
    private Groupp  group   = new Groupp();
    private Filter  filter  = new Filter();
    private Comment comment = new Comment();

    private UserDataManager    userDataManager    = new UserDataManager();
    private GroupDataManager   groupDataManager   = new GroupDataManager();
    private EventDataManager   eventDataManager   = new EventDataManager();
    private FilterDataManager  filterDataManager  = new FilterDataManager();
    private CommentDataManager commentDataManager = new CommentDataManager();

    @Before
    public void setUp() throws Exception {

        group.setGroupName("group_test");
        group.setGroupAdmin("name_test");

        event.setEventName("event_test");
        event.setDate("1.10.2014");
        event.setCoordinates("1444:2111");
        event.setLatitude(100F);
        event.setLongitude(100F);
        event.setEventAdmin("name_test");

        comment.setCommentName("comment_test");
        comment.setComment("comment_test description");
        //comment.setEvent(event);

        user.setUserName("name_test");
        user.setPassword("password_test");
        user.setRole("admin");
        user.getGroups().add(group);
        user.getEvents().add(event);

        filter.setFilterName("Filter_test");
        filter.setFilterData("asd:asd, dsa:dsa");
        filter.setUser(user);

        group.getEvents().add(event);
        group.getUsers().add(user);

        eventDataManager.saveNewEvent(event);
        groupDataManager.saveNewGroup(group);
        userDataManager.saveNewUser(user);
        commentDataManager.saveNewComment(comment);
        filterDataManager.saveNewFilter(filter);

    }

    @After
    public void tearDown() throws Exception {

        userDataManager.deleteUser(user);
        //eventDataManager.deleteEvent(event);
        //groupDataManager.deleteGroup(group);
        //commentDataManager.deleteComment(comment);
        //filterDataManager.deleteFilter(filter);

    }

    @Test
    public void testAdd() {
        assertEquals("Error", "asd", "asd");
    }
*/
}