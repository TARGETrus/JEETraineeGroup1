package servlets;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
class JSONEditProfileServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("user");

        String pass = req.getParameter("old_pwd");
        String newPass = req.getParameter("new_pwd");

        String str = null;

        if (!pass.equals(newPass) && pass.length() != 0 && newPass.length() != 0) {

            String userName = null;
            Cookie[] cookies = req.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("username")) userName = cookie.getValue();
                }
            }
            if(userName == null)
                resp.sendRedirect("login.html");

           if(name.length() == 0){
               JSONObject obj = new JSONObject();
               obj.put("name", "pass_change");
               str = obj.toJSONString();

               //TODO password change
           } else {
               JSONObject obj = new JSONObject();
               obj.put("name", "name_and_pass_change");
               str = obj.toJSONString();
               //TODO password change
               //TODO name change
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


}
