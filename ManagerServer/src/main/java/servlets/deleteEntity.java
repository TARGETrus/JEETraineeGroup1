package servlets;

import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import DAOHandler.UserDataManager;
import model.Event;
import model.Groupp;
import model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_entity")
public class deleteEntity extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

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

        JSONObject obj = new JSONObject();

        String entityType = req.getParameter("entityType");
        String entityName = req.getParameter("entityName");
        String str        = null;

        switch (entityType) {

            case "user" : {

                str = deleteUser(entityName);
                break;

            }

            case "event" : {

                str = deleteEvent(entityName);
                break;

            }

            case "group" : {

                str = deleteGroup(entityName);
                break;

            }

            default : {

                obj.put("name", "No such entity!");
                str = obj.toJSONString();
                break;

            }

        }

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);
        resp.sendRedirect("adminpanel.jsp");

    }

    private String deleteUser(String entityName) {

        JSONObject obj = new JSONObject();
        String str = null;

        if (!entityName.equals("")) {

            User user = userDataManager.getUserData(entityName);

            if (user != null) {

                if (userDataManager.deleteUser(user)) {

                    obj.put("name", "user removed!");
                    str = obj.toJSONString();

                } else {

                    obj.put("name", "db error!");
                    str = obj.toJSONString();

                }

            } else {

                obj.put("name", "invalid user name!");
                str = obj.toJSONString();

            }

        } else {

            obj.put("name", "invalid data!");
            str = obj.toJSONString();

        }

        return str;

    }

    private String deleteEvent(String entityName) {

        JSONObject obj = new JSONObject();
        String str = null;

        if (!entityName.equals("")) {

            Event event = eventDataManager.getEventData(entityName);

            if (event != null) {

                if (eventDataManager.deleteEvent(event)) {

                    obj.put("name", "event removed!");
                    str = obj.toJSONString();

                } else {

                    obj.put("name", "db error!");
                    str = obj.toJSONString();

                }

            } else {

                obj.put("name", "invalid event name!");
                str = obj.toJSONString();

            }

        } else {

            obj.put("name", "invalid data!");
            str = obj.toJSONString();

        }

        return str;

    }

    private String deleteGroup(String entityName) {

        JSONObject obj = new JSONObject();
        String str = null;

        if (!entityName.equals("")) {

            Groupp group = groupDataManager.getGroupData(entityName);

            if (group != null) {

                if (groupDataManager.deleteGroup(group)) {

                    obj.put("name", "group removed!");
                    str = obj.toJSONString();

                } else {

                    obj.put("name", "db error!");
                    str = obj.toJSONString();

                }

            } else {

                obj.put("name", "invalid group name!");
                str = obj.toJSONString();

            }

        } else {

            obj.put("name", "invalid data!");
            str = obj.toJSONString();

        }

        return str;

    }

}