package tmp;

import java.math.BigDecimal;
import java.util.List;


import model.Comment;
import model.Event;
import model.Group;
import model.User;

import org.junit.Test;


import DAO.HibernateUtil;
import DAOHandler.CommentDataManager;
import DAOHandler.UserDataManager;

import junit.framework.TestCase;


//temporary test 
public class DaoHibernateTest extends TestCase {
	@Test
	public void test() throws InterruptedException 
	{
		 UserDataManager userDataManager       = new UserDataManager();
	        CommentDataManager commentDataManager = new CommentDataManager();

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

	        //userDataManager.saveNewUser(testUser);
	        //commentDataManager.saveNewComment(comment);
	        User singleUser = userDataManager.findUserById(1);
	        System.out.println(singleUser.toString());
	        //System.out.println(singleUser.getEvents().toString());

	        List<User> allUsers = userDataManager.findAllUsers();

	        User userLogin = userDataManager.getUserData("name");
	        //System.out.println(userLogin);

	        HibernateUtil.closeFactory();

	}
}
