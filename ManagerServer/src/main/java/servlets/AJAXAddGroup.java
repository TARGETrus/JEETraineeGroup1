package servlets;

import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import model.Groupp;
import model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_group")
public class AJAXAddGroup extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Autowired
    private GroupDataManager groupDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String groupName = req.getParameter("groupName");
        String username = req.getParameter("username");
        String addUser = req.getParameter("usersList");
        String addEvent = req.getParameter("eventsList");
        String str = null;

        if (groupName.length() != 0) {

            //EventDataManager eventDataManager = new EventDataManager();
            JSONObject obj = new JSONObject();
            obj.put("name", "add_group");
            str = obj.toJSONString();
            Groupp group = new Groupp();
            group.setGroupName(groupName);
            group.setGroupAdmin(username);
            //group.getEvents().add(eventDataManager.getEventData(addEvent));
            //group.getUsers().add(userDataManager.getUserData(username));
            //group.getUsers().add(userDataManager.getUserData(addUser));
            groupDataManager.saveNewGroup(group);
            User gotUser = userDataManager.getUserCompleteData(username);
            gotUser.getGroups().add(group);
            userDataManager.modifyUser(gotUser);

        } else {

            JSONObject obj = new JSONObject();
            obj.put("name", "error");
            str = obj.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}
