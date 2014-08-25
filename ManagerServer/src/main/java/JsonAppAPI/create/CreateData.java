package JsonAppAPI.create;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAOHandler.CommentDataManager;
import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import JsonAppAPI.jsonustil.JsonGroupModel;
import model.Comment;
import model.Event;
import model.Groupp;
import model.User;
import org.json.JSONObject;

@WebServlet("/json/create_data")
public class CreateData extends HttpServlet{

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
            case "create_user":{
                String json = req.getParameter("json");
                status = parseUserJson(json);
                break;
            }
            case "create_event":{
                String json = req.getParameter("json");
                status = parseEventJson(json);
                break;
            }
            case "create_group":{
                String json = req.getParameter("json");
                status = parseGroupJson(json);
                break;
            }
            case "create_comment":{//для эвента
                String json = req.getParameter("json");
                status = parseCommentJson(json);
                break;
            }
            default:{
                requset.put("error", "incorrect data");
                requset.put("Status", "error");
                status = requset.toString();
                break;
            }
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(status);
    }

    private String parseUserJson(String json){

        JSONObject object = new JSONObject(json);
        String name = (String) object.get("username");
        String pass = (String) object.get("password");
        String role = (String) object.get("role");
        if(name.length() != 0 & pass.length() != 0 && role.length() != 0){
            User user = new User();
            user.setUserName(name);
            user.setPassword(pass);
            user.setRole(role);
            userDataManager.saveNewUser(user);
            requset.put("Status", "OK");
        }else{
            requset.put("error", "incorrect data");
            requset.put("Status", "error");
        }
        return requset.toString();
    }

    private String parseEventJson(String json){

        JSONObject object = new JSONObject(json);

        String eventname = (String) object.get("eventname");
        String admin = (String) object.get("admin");
        String lat = (String) object.get("lat");
        String lng = (String) object.get("lng");
        String address = (String) object.get("address");
        String date = (String) object.get("date");
        userDataManager.getUserData(admin);

        if(     eventname.length() != 0 &&
                userDataManager.getUserData(admin) != null &&
                lat.length() != 0 &&
                lng.length() != 0 &&
                address.length() != 0 &&
                date.length() != 0){

            Event event = new Event();
            event.setLatitude(Float.parseFloat(lat));
            event.setLongitude(Float.parseFloat(lng));
            event.setEventAdmin(admin);
            event.setDate(date);
            event.setCoordinates(address);
            event.setEventName(eventname);
            eventDataManager.saveNewEvent(event);
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
        String admin = (String) object.get("admin");


        if(userDataManager.getUserData(admin) != null && name.length() != 0){
            Groupp group = new Groupp();
            group.setGroupName(name);
            group.setGroupAdmin(admin);
            groupDataManager.saveNewGroup(group);
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
        String commentdata = (String) object.get("comment");
        String eventname = (String) object.get("eventname");
        if(commentdata.length() != 0 && name.length() != 0 && eventDataManager.getEventData(eventname) != null){
            Comment comment = new Comment();
            comment.setCommentName(name);
            comment.setComment(commentdata);
            comment.setEvent(eventDataManager.getEventData(eventname));
            commentDataManager.saveNewComment(comment);
            requset.put("Status", "OK");
        }else{
            requset.put("error", "incorrect data");
            requset.put("Status", "error");
        }

        return requset.toString();
    }

}
