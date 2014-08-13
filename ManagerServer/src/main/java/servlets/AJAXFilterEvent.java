package servlets;


import DAOHandler.EventDataManager;
import DAOHandler.FilterDataManager;
import model.Event;
import model.Filter;
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
        String radius = req.getParameter("radius");
        String userFilter = req.getParameter("userFilter");
        String json;
        if(eventFilter.length() != 0 || radius.length()!= 0 || userFilter.length() != 0){
            Filter filter = new Filter();
            EventDataManager eventDataManager = new EventDataManager();
            FilterDataManager filterDataManager = new FilterDataManager();
            ArrayList<Event> events = new ArrayList<>(eventDataManager.getAllEvents());//TODO инициализация юзеров
            filter.setFilterName(eventFilter);
//            filter.set
            JSONArray jsonarray = new JSONArray();
            for(Event event: events){
                ArrayList<String> eusers = new ArrayList<>();
                JSONObject jsonEvent = new JSONObject();
//               for(String user: event.getUsers()){
//                    eusers.add(user.getUserName());
//               }
                jsonEvent.put("userlist", eusers);
                jsonEvent.put("lat", event.getLatitude());
                jsonEvent.put("lng", event.getLongitude());
                jsonEvent.put("eventname", event.getEventName());
                jsonEvent.put("address", event.getCoordinates());
                jsonarray.add(jsonEvent);
            }
            json = jsonarray.toJSONString();




            //search form
            List<Event> searchEvent = eventDataManager.searchEventData("event");

            if (searchEvent != null) {
                System.out.println("event " + searchEvent.toString());
            } else {
                System.out.println("Nothing found!!!");
            }

            List<Event> collEvent = eventDataManager.searchByEventCollectionsData("name", "group");

            if (collEvent != null) {
                System.out.println("Collect " + collEvent.toString());
            } else {
                System.out.println("Nothing found!!!");
            }

            List<Event> closeEvent = eventDataManager.getCloseEventData(0F, 0F, 1500F);

            if (closeEvent != null) {
                System.out.println("close " + closeEvent.toString());
            } else {
                System.out.println("Nothing found!!!");
            }

            List<Event> filtEvent = eventDataManager.getFilteredEventData(0F, 0F, null, null, "event", null);

            if (filtEvent != null) {
                System.out.println("filter " + filtEvent.toString());
            } else {
                System.out.println("Nothing found!!!");
            }





        }else {
            JSONObject obj = new JSONObject();
            obj.put("message", "error");
            json = obj.toJSONString();
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }
}
