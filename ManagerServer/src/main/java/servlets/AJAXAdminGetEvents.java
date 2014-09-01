package servlets;

import DAOHandler.EventDataManager;
import model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin_event_json")
public class AJAXAdminGetEvents extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EventDataManager eventDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = null;
        JSONArray jsonarray = new JSONArray();

        ArrayList<Event> events = new ArrayList<>(eventDataManager.getAllEvents());

        for(Event event : events){

            JSONObject jsonUser = new JSONObject();

            jsonUser.put("eventname", event.getEventName());
            jsonarray.add(jsonUser);

            json = jsonarray.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }

}