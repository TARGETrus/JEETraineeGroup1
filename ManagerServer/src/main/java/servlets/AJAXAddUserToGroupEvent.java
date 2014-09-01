package servlets;

import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/follow")
public class AJAXAddUserToGroupEvent extends AbstractAutowiringServlet {

    @Autowired
    private UserDataManager userDataManager;

    @Autowired
    private EventDataManager eventDataManager;

    @Autowired
    private GroupDataManager groupDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("username");
        String flag =  req.getParameter("flag");
        String json;

        switch (flag) {

            case "group":{

                String groupName = req.getParameter("group");
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