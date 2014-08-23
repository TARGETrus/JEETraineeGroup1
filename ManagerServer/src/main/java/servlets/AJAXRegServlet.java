package servlets;


import DAOHandler.UserDataManager;
import model.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg_servlet")
public class AJAXRegServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("user");
        String repeatName = req.getParameter("repeat_user");
        String pass = req.getParameter("password");
        String repeatPass = req.getParameter("repeat_password");

        String str = null;

        if (name.equals(repeatName) && pass.equals(repeatPass)) {

            if (!(name.length() == 0)) {

                UserDataManager manager = new UserDataManager();
                String username = new String();
                User user = manager.getUserData(name);
//                username = manager.getUserData(name).getUserName();

                if (user != null) {

                    JSONObject obj = new JSONObject();
                    obj.put("name", "exist");
                    str = obj.toJSONString();

                } else {

                    JSONObject obj = new JSONObject();
                    obj.put("name", "not_exist");
                    str = obj.toJSONString();
                    User newUser = new User();
                    newUser.setUserName(name);
                    newUser.setPassword(pass);
                    manager.saveNewUser(newUser);

                }

            } else {

                JSONObject obj = new JSONObject();
                obj.put("name", "invalid_data");
                str = obj.toJSONString();

            }

        } else {

            JSONObject obj = new JSONObject();
            obj.put("name", "pss_or_name_incorrect");
            str = obj.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}