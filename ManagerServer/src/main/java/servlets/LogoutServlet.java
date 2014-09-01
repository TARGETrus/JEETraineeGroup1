package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends AbstractAutowiringServlet {

    static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        Cookie loginCookie = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {

            for (Cookie cookie: cookies) {

                if (cookie.getName().equals("user")) {

                    loginCookie = cookie;
                    break;

                }

            }

        }

        if (loginCookie != null) {

            loginCookie.setMaxAge(0);
            resp.addCookie(loginCookie);

        }

        resp.sendRedirect("login.html");

    }

}