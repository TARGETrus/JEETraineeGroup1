package servlets;

import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import model.Event;
import model.Groupp;
import model.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_group_servlet")
public class AJAXAdminEditGroup extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDataManager  userDataManager  = new UserDataManager();
    private EventDataManager eventDataManager = new EventDataManager();
    private GroupDataManager groupDataManager = new GroupDataManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String groupCurrName = req.getParameter("curr_name");
        String groupName     = req.getParameter("group");
        String group_admin   = req.getParameter("group_admin");
        String user_coll     = req.getParameter("user_collection");
        String event_coll    = req.getParameter("event_collection");

        String str = null;

        JSONObject obj = new JSONObject();
        obj.put("newName", groupCurrName);

        Groupp group = groupDataManager.getGroupCompleteData(groupCurrName);

        if (group_admin.length() != 0) {

            obj.put("name", "change successful!");
            group.setGroupAdmin(group_admin);

        }

        if (user_coll.length() != 0) {

            User user = userDataManager.getUserData(user_coll);
            group.getUsers().add(user);
            obj.put("name", "change successful!");

        }

        if (event_coll.length() != 0) {

            Event event = eventDataManager.getEventData(event_coll);
            group.getEvents().add(event);
            obj.put("name", "change successful!");

        }

        groupDataManager.modifyGroup(group);

        if (groupName.length() != 0) {

            obj.put("name", "change successful!");
            obj.put("newName", groupName);
            groupDataManager.changeGroupName(groupCurrName, groupName);

        }

        if (groupName.length() == 0 && group_admin.length() == 0 && user_coll.length() == 0 && event_coll.length() == 0) {

            obj.put("name", "invalid_data!");

        }

        str = obj.toJSONString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}