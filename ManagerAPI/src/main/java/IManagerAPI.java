import java.util.Map;

public interface IManagerAPI {

    void createUser(String userName, String password, int userID);
    boolean login(String userName, String password);
    void modifyUser(int userID);//through getEvent method
    void getUser(int userID); //or id
    void removeUser(int userID);
    void createEvent(String userName, String coordinates, String date, String eventName, int eventID);
    void getEvent(int eventID);//
    void modifyEvent(int eventID);//through getEvent method
    void createGroup(String groupName, int groupID);
    void getGroup(int groupID);//
    void modgifyGroup(int groupID);
    void createComment(String userName, String commentName, String comment, int commentID);
    void getComment(int commentID);//
    void modgifyComment(int commentID);


    Map<Integer, String> showUsers();
    Map<Integer, String> showEvents();
    Map<Integer, String> showGroups();
    Map<Integer, String> showComments();



}
