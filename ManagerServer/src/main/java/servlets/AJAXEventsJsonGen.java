package servlets;

import DAOHandler.UserDataManager;
import model.Event;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/eventjson")
public class AJAXEventsJsonGen extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String json;
        JSONArray jsonarray = new JSONArray();

        if (username.length() != 0) {

            ArrayList<Event> events = new ArrayList<>(userDataManager.getUserCompleteData(username).getEvents());

            for (Event event: events) {

                ArrayList<String> eusers = new ArrayList<>();
                JSONObject jsonEvent = new JSONObject();

                for (User user: event.getUsers()) {
                    eusers.add(user.getUserName());
                }

                jsonEvent.put("userlist", eusers);
                jsonEvent.put("lat", event.getLatitude());
                jsonEvent.put("lng", event.getLongitude());
                jsonEvent.put("eventname", event.getEventName());
                jsonEvent.put("address", event.getCoordinates());
                jsonarray.add(jsonEvent);
            }

            json = jsonarray.toJSONString();

        } else {

            JSONObject obj = new JSONObject();
            obj.put("message", "error");
            json = obj.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

}