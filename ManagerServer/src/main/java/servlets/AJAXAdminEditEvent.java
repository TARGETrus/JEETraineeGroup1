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

@WebServlet("/edit_event_servlet")
public class AJAXAdminEditEvent extends HttpServlet {

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

        String eventCurrName = req.getParameter("curr_name");
        String eventName     = req.getParameter("event");
        String coordinates   = req.getParameter("coordinates");
        String latitude      = req.getParameter("latitude");
        String longitude     = req.getParameter("longitude");
        String date          = req.getParameter("date");
        String event_admin   = req.getParameter("event_admin");
        String user_coll     = req.getParameter("user_collection");
        String group_coll    = req.getParameter("group_collection");

        String str = null;

        JSONObject obj = new JSONObject();
        obj.put("newName", eventCurrName);

        Event event = eventDataManager.getEventCompleteData(eventCurrName);

        if (coordinates.length() != 0) {

            obj.put("name", "change successful!");
            event.setCoordinates(coordinates);

        }

        if (latitude.length() != 0) {

            Float lat = Float.parseFloat(latitude);
            obj.put("name", "change successful!");
            event.setLatitude(lat);

        }

        if (longitude.length() != 0) {

            Float lon = Float.parseFloat(longitude);
            obj.put("name", "change successful!");
            event.setLongitude(lon);

        }

        if (date.length() != 0) {

            date = date.replace("T", " ");
            obj.put("name", "change successful!");
            event.setDate(date);

        }

        if (event_admin.length() != 0) {

            obj.put("name", "change successful!");
            event.setEventAdmin(event_admin);

        }

        if (user_coll.length() != 0) {

            User user = userDataManager.getUserData(user_coll);
            event.getUsers().add(user);
            obj.put("name", "change successful!");

        }

        if (group_coll.length() != 0) {

            Groupp group = groupDataManager.getGroupData(group_coll);
            event.getGroups().add(group);
            obj.put("name", "change successful!");

        }

        eventDataManager.modifyEvent(event);

        if (eventName.length() != 0) {

            obj.put("name", "change successful!");
            obj.put("newName", eventName);
            eventDataManager.changeEventName(eventCurrName, eventName);

        }

        if (eventName.length() == 0 && coordinates.length() == 0 && latitude.length() == 0 && longitude.length() == 0 && date.length() == 0 && event_admin.length() == 0 && user_coll.length() == 0 && group_coll.length() == 0) {

            obj.put("name", "invalid_data!");

        }

        str = obj.toJSONString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}