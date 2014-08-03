package DAOHandler;

import DAO.HibernateUtil;
import model.Comment;
import model.Event;
import model.Group;
import model.User;

public class HibernateTest {

    public static void main(String[] args) {

        UserDataManager dataManager = new UserDataManager();

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

        //dataManager.saveNewUser(testUser);
        //dataManager.saveNewComment(comment);
        User singleUser = dataManager.findUserById(1);
        System.out.println(singleUser.toString());
        //System.out.println(singleUser.getEvents().toString());

        //dataManager.mergeUser(singleUser);

        //List<User> allUsers = dataManager.findAllUsers();
        //System.out.println(allUsers.toString());

        //User userLogin = dataManager.getUserData("name");
        //System.out.println(userLogin);

        HibernateUtil.closeFactory();

    }

}