package DAO;

import model.Comment;
import model.Event;
import model.Group;
import model.User;

public interface ServerDAO {

    boolean insertUser(User user);
    User selectUser(int userID);// что-то мне кажется селект по АЙДИ не очень удобным...
    boolean removeUser(User user);
    boolean updateUser(User user);

    boolean insertEvent(Event event);
    Event selectEvent(int eventID);
    boolean removeEvent(Event event);
    boolean updateEvent(Event event);

    boolean insertGroup(Group group);
    Group selectGrouo(int groupID);
    boolean removeGroup(Group group);
    boolean updateGroup(Group group);

    boolean insertComment(Comment comment);
    Comment selectComment(int commentID);
    boolean removeComment(Comment comment);
    boolean updateComment(Comment comment);


}