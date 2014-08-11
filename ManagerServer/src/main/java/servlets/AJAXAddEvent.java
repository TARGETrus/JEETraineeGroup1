package servlets;

import DAOHandler.EventDataManager;
import DAOHandler.UserDataManager;
import model.Event;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_event")
public class AJAXAddEvent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("eventName");
        String date = req.getParameter("date");
        date = date.replace("T", " ");
        Float lng = 100F; //req.getParameter("lng");
        Float lat = 100F;//req.getParameter("lat");
        String address = req.getParameter("coord");

        String str = null;

        if(!date.equals(null) && !eventName.equals(null) && lng.equals(null) && lat.equals(null)){
            JSONObject obj = new JSONObject();
            obj.put("name", "add_event");
            str = obj.toJSONString();
            EventDataManager manager = new EventDataManager();
            Event event = new Event();
            event.setLatitude(lat);
            event.setLongitude(lng);
            event.setDate(date);
            event.setEventName(eventName);
            event.setCoordinates(address);
            UserDataManager userDataManager = new UserDataManager();
            //TODO сделать добавление одного эвента для юзера
           manager.saveNewEvent(event);
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
