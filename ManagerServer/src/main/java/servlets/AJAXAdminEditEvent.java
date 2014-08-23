package servlets;

import DAOHandler.EventDataManager;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EventDataManager eventDataManager = new EventDataManager();

        String           eventCurrName    = req.getParameter("curr_name");
        String           eventName        = req.getParameter("event");
        String           coordinates      = req.getParameter("coordinates");
        String           latitude         = req.getParameter("latitude");
        String           longitude        = req.getParameter("longitude");
        String           date             = req.getParameter("date");
        String           event_admin      = req.getParameter("event_admin");

        String str = null;

        JSONObject obj = new JSONObject();
        obj.put("newName", eventCurrName);

        if (coordinates.length() != 0) {

            obj.put("name", "change successful!");
            eventDataManager.changeEventCoordinates(eventCurrName, coordinates);

        }

        if (latitude.length() != 0) {

            Float lat = Float.parseFloat(latitude);
            obj.put("name", "change successful!");
            eventDataManager.changeEventLatitude(eventCurrName, lat);

        }

        if (longitude.length() != 0) {

            Float lon = Float.parseFloat(longitude);
            obj.put("name", "change successful!");
            eventDataManager.changeEventLongitude(eventCurrName, lon);

        }

        if (date.length() != 0) {

            date = date.replace("T", " ");
            obj.put("name", "change successful!");
            eventDataManager.changeEventDate(eventCurrName, date);

        }

        if (event_admin.length() != 0) {

            obj.put("name", "change successful!");
            eventDataManager.changeEventAdmin(eventCurrName, event_admin);

        }

        if (eventName.length() != 0) {

            obj.put("name", "change successful!");
            obj.put("newName", eventName);
            eventDataManager.changeEventName(eventCurrName, eventName);

        }

        if (eventName.length() == 0 && coordinates.length() == 0 && latitude.length() == 0 && longitude.length() == 0 && date.length() == 0 && event_admin.length() == 0) {

            obj.put("name", "invalid_data!");

        }

        str = obj.toJSONString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}
