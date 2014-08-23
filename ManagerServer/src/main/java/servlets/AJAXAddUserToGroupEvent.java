package servlets;


import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import model.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/follow")
public class AJAXAddUserToGroupEvent extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        String flag =  req.getParameter("flag");
        String json;

        switch (flag){
            case "group":{
                String groupName = req.getParameter("group");
                UserDataManager userDataManager = new UserDataManager();
                GroupDataManager groupDataManager = new GroupDataManager();
                User gotUser = userDataManager.getUserCompleteData(name);
                gotUser.getGroups().add(groupDataManager.getGroupCompleteData(groupName));
                userDataManager.modifyUser(gotUser);
                JSONObject object = new JSONObject();
                object.put("message", "OK");
                json = object.toJSONString();
                break;
            }
            case "event":{
                String eventName = req.getParameter("event");
                UserDataManager userDataManager = new UserDataManager();
                EventDataManager eventDataManager = new EventDataManager();
                User gotUser = userDataManager.getUserCompleteData(name);
                gotUser.getEvents().add(eventDataManager.getEventCompleteData(eventName));
                userDataManager.modifyUser(gotUser);
                JSONObject object = new JSONObject();
                object.put("message", "OK");
                json = object.toJSONString();
                break;
            }
            default:{
                JSONObject object = new JSONObject();
                object.put("message", "error");
                json = object.toJSONString();
            }
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }
}
