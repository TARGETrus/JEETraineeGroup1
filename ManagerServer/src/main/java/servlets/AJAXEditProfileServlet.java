package servlets;

import DAOHandler.UserDataManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/edit_servlet")
public class AJAXEditProfileServlet extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String userName = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("username")) userName = cookie.getValue();

            }

        }

        String name = req.getParameter("user");
        String pass = req.getParameter("old_pwd");
        String newPass = req.getParameter("new_pwd");

        String str = null;

        if (name.length() != 0 || (newPass.length() != 0 && pass.length() != 0 && pass.equals(new UserDataManager().getUserData(userName).getPassword()))) {

            if (name.length() == 0) {

                JSONObject obj = new JSONObject();
                obj.put("name", "pass_change");
                str = obj.toJSONString();
                userDataManager.changeUserPassword(userName, newPass);

            } else if (newPass.length() == 0) {

                JSONObject obj = new JSONObject();
                obj.put("name", "name_change");
                str = obj.toJSONString();
                changeCookieName(cookies, name, resp);
                userDataManager.changeUserName(userName, name);

            } else {

                JSONObject obj = new JSONObject();
                obj.put("name", "name_and_pass_change");
                str = obj.toJSONString();
                changeCookieName(cookies, name, resp);
                userDataManager.changeUserPassword(userName, newPass);
                userDataManager.changeUserName(userName, name);

            }

        } else {

            JSONObject obj = new JSONObject();
            obj.put("name", "incorrect_pass_data");
            str = obj.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

    private void changeCookieName( Cookie[] cookies, String name, HttpServletResponse resp) {

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("username")) {

                cookie.setMaxAge(0);
                resp.addCookie(cookie);

            }

        }

        Cookie loginCookie = new Cookie("username",name);
        //setting cookie to expiry in 30 mins
        loginCookie.setMaxAge(30 * 60);
        resp.addCookie(loginCookie);

    }

}