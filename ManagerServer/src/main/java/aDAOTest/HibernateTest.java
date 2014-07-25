package aDAOTest;

import DAO.HibernateUtil;
import model.Comment;
import model.Event;
import model.Group;
import model.User;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        TestManager testManager = new TestManager();

        Group group = new Group();
        group.setGroupName("group");

        Event event = new Event();
        event.setEventName("event");
        event.setDate("1.10.2014");
        event.setCoordinates("1444:2111");
        event.setLatitude("111");
        event.setLongitude("111");
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

        //testManager.saveNewUser(testUser);
        //testManager.saveNewComment(comment);
        User singleUser = testManager.findUserById(1);
        System.out.println(singleUser.toString());
        System.out.println(singleUser.getEvents().toString());

        //testManager.mergeUser(singleUser);

        List<User> allUsers = testManager.findAllUsers();
        System.out.println(allUsers.toString());

        HibernateUtil.closeFactory();

    }

}