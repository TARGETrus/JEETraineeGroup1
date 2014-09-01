package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Event;
import model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import DAOHandler.EventDataManager;
import DAOHandler.UserDataManager;

@WebServlet("/add_event")
public class AJAXAddEvent extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Autowired
    private EventDataManager eventDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String eventName = req.getParameter("eventName");
        String username = req.getParameter("username");
        String date = req.getParameter("date");
        date = date.replace("T", " ");
        Float lng = Float.parseFloat(req.getParameter("lng"));
        Float lat = Float.parseFloat(req.getParameter("lat"));
        String address = req.getParameter("coord");

        String str = null;

        if (!date.equals(null) && !eventName.equals(null) && !lng.equals(null) && !lat.equals(null)) {

            JSONObject obj = new JSONObject();
            obj.put("name", "add_event");
            str = obj.toJSONString();
            Event event = new Event();
            event.setLatitude(lat);
            event.setLongitude(lng);
            event.setDate(date);
            event.setEventName(eventName);
            event.setCoordinates(address);
            event.setEventAdmin(username);
            eventDataManager.saveNewEvent(event);
            User gotUser = userDataManager.getUserCompleteData(username);
            gotUser.getEvents().add(event);
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