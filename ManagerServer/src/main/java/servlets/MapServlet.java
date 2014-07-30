package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet{

    static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        resp.addCookie(new Cookie("latitude", "-25.363882"));
        resp.addCookie(new Cookie("longitude", "131.044922"));
        resp.sendRedirect("map.jsp");

    }
}
