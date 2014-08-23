package servlets;


import DAOHandler.EventDataManager;
import model.Event;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/eventfilterjson")
public class AJAXFilterEvent extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String eventFilter = req.getParameter("eventFilter");
        if(eventFilter.length() == 0)
            eventFilter = null;
        String rad = req.getParameter("radius");
        String userFilter = req.getParameter("userFilter");
        if(userFilter.length() == 0)
            userFilter = null;
        String point = req.getParameter("point");
        if(point.length() == 0)
            point = null;
        String lat = req.getParameter("lat");
        String lng = req.getParameter("lng");
        String json;
        float latitude = 0;
        float longitude = 0;
        float radius = 0;
        if(rad.length() != 0)
        {
           latitude = Float.parseFloat(lat);
            longitude = Float.parseFloat(lng);
            radius = Float.parseFloat(rad);
        }
        if(userFilter != null || eventFilter != null || point != null){

            EventDataManager eventDataManager = new EventDataManager();
            List<Event> events = eventDataManager.getFilteredEventData(latitude, longitude, radius, userFilter, eventFilter, null);
            JSONArray jsonarray = new JSONArray();
            for(Event event: events){
                ArrayList<String> eusers = new ArrayList<>();
                JSONObject jsonEvent = new JSONObject();
                for(User user: event.getUsers()){
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

        }else {
            EventDataManager eventDataManager = new EventDataManager();
            ArrayList<Event> events = new ArrayList<>(eventDataManager.getAllEvents());
            JSONArray jsonarray = new JSONArray();
            for(Event event: events){
                ArrayList<String> eusers = new ArrayList<>();
                JSONObject jsonEvent = new JSONObject();
                for(User user: event.getUsers()){
                    eusers.add(user.getUserName());
                }
                jsonEvent.put("userlist", eusers);
                jsonEvent.put("lat", event.getLatitude());
                jsonEvent.put("lng", event.getLongitude());
                jsonEvent.put("eventname", event.getEventName());
                jsonEvent.put("address", event.getCoordinates());
                jsonarray.add(jsonEvent);
            }
            json = jsonarray.toJSONString();;
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }
}
