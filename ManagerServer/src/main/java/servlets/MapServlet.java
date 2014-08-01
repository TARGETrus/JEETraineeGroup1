package servlets;

import aDAOTest.TestManager;
import model.Event;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet {

    TestManager testManager = new TestManager();
    User user;
    Event event;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        user = testManager.findUserById(Integer.parseInt(req.getParameter("user_id")));
        event = user.getEvents().iterator().next(); //get one user's event

        resp.addCookie(new Cookie("latitude", event.getLatitude()));
        resp.addCookie(new Cookie("longitude", event.getLongitude()));

        resp.sendRedirect("map.jsp");
    }

}
