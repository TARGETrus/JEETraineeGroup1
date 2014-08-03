package servlets;

import DAOHandler.UserDataManager;
import model.Event;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {
    static final long serialVersionUID = 1L;

    UserDataManager dataManager = new UserDataManager();
    User user;
    List<Event> events;
    String lat = "";
    String lon = "";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        user = dataManager.findUserById(Integer.parseInt(req.getParameter("user_id")));
        events = new ArrayList<>(user.getEvents());
        for(int i = 0; i < events.size(); i++) {
            lat += events.get(i).getLatitude() + " ";
            lon += events.get(i).getLongitude() + " ";
        }

        resp.addCookie(new Cookie("latitude", lat));
        resp.addCookie(new Cookie("longitude", lon));

        resp.sendRedirect("map.jsp");
    }

}
