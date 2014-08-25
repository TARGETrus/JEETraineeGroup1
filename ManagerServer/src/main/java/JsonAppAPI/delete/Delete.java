package JsonAppAPI.delete;



import DAOHandler.CommentDataManager;
import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import model.Comment;
import model.Event;
import model.Groupp;
import model.User;
import org.json.JSONObject;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/json/delete_date")
public class Delete extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private JSONObject requset = new JSONObject();
    private String status = new String();
    private EventDataManager eventDataManager = new EventDataManager();
    private UserDataManager userDataManager = new UserDataManager();
    private GroupDataManager groupDataManager = new GroupDataManager();
    private CommentDataManager commentDataManager = new CommentDataManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action){
            case "delete_user":{
                String json = req.getParameter("json");
                status = parseUserJson(json);
                break;
            }
            case "delete_event":{
                String json = req.getParameter("json");
                status = parseEventJson(json);
                break;
            }
            case "delete_group":{
                String json = req.getParameter("json");
                status = parseGroupJson(json);
                break;
            }
            case "delete_comment":{
                String json = req.getParameter("json");
                status = parseCommentJson(json);
            }
            default:{
                break;
            }
        }

    }

    private String parseUserJson(String json){

        JSONObject object = new JSONObject(json);
        String name = (String) object.get("username");
        String pass = (String) object.get("password");

        if(name.length() != 0 & pass.length() != 0 && userDataManager.getUserData(name) != null){
            if(userDataManager.getUserData(name).getPassword().equals(pass))
            {
                userDataManager.deleteUser(userDataManager.getUserCompleteData(name));
                requset.put("Status", "OK");
            }else {
                requset.put("error", "incorrect password");
                requset.put("Status", "error");
            }
        }else{
            requset.put("error", "incorrect data");
            requset.put("Status", "error");
        }
        return requset.toString();
    }

    private String parseEventJson(String json){

        JSONObject object = new JSONObject(json);
        String eventname = (String) object.get("eventname");

        if(eventname.length() != 0 && eventDataManager.getEventData(eventname) != null){

            eventDataManager.deleteEvent(eventDataManager.getEventData(eventname));
            requset.put("Status", "OK");

        }else{
            requset.put("error", "incorrect data");
            requset.put("Status", "error");
        }

        return requset.toString();
    }

    private String parseGroupJson(String json){

        JSONObject object = new JSONObject(json);
        String name = (String) object.get("groupname");

        if(groupDataManager.getGroupData(name) != null && name.length() != 0){
            groupDataManager.deleteGroup(groupDataManager.getGroupData(name));
            requset.put("Status", "OK");
        }else{
            requset.put("error", "incorrect data");
            requset.put("Status", "error");
        }

        return requset.toString();
    }

    private String parseCommentJson(String json){

        JSONObject object = new JSONObject(json);
        String name = (String) object.get("commentname");
        if(name.length() != 0 && commentDataManager.getCommentData(name) != null){
            commentDataManager.deleteComment(commentDataManager.getCommentData(name));
            requset.put("Status", "OK");
        }else{
            requset.put("error", "incorrect data");
            requset.put("Status", "error");
        }

        return requset.toString();
    }

}
