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

@WebServlet("/edit_user_servlet")
public class AJAXAdminEditUser extends HttpServlet {

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

        String userCurrName = req.getParameter("curr_name");
        String nameName     = req.getParameter("user");
        String pass         = req.getParameter("old_pwd");
        String newPass      = req.getParameter("new_pwd");
        String role         = req.getParameter("role");
        String event_coll   = req.getParameter("event_collection");
        String group_coll   = req.getParameter("group_collection");

        String str = null;

        JSONObject obj = new JSONObject();
        obj.put("newName", userCurrName);

        User user = userDataManager.getUserCompleteData(userCurrName);

        if ((newPass.length() != 0 && pass.length() != 0 && pass.equals(new UserDataManager().getUserData(userCurrName).getPassword()))) {

            user.setPassword(newPass);
            obj.put("name", "change successful!");

        }

        if (role.length() != 0) {

            user.setRole(role);
            obj.put("name", "change successful!");

        }

        if (event_coll.length() != 0) {

            Event event = eventDataManager.getEventData(event_coll);
            user.getEvents().add(event);
            obj.put("name", "change successful!");

        }

        if (group_coll.length() != 0) {

            Groupp group = groupDataManager.getGroupData(group_coll);
            user.getGroups().add(group);
            obj.put("name", "change successful!");

        }

        userDataManager.modifyUser(user);

        if (nameName.length() != 0) {

            obj.put("name", "change successful!");
            obj.put("newName", nameName);
            userDataManager.changeUserName(userCurrName, nameName);

        }

        if (nameName.length() == 0 && newPass.length() == 0 && role.length() == 0 && event_coll.length() == 0 && group_coll.length() == 0) {

            obj.put("name", "invalid_data!");

        }

        str = obj.toJSONString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}