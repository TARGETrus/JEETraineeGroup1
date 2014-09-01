package servlets;

import DAOHandler.UserDataManager;
import model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_user")
public class AJAXAddUser extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String str = null;

        if (!userName.equals("") && !password.equals("") && !role.equals("")) {

            User user = userDataManager.getUserData(userName);

            if (user != null) {

                JSONObject obj = new JSONObject();
                obj.put("name", "exist");
                str = obj.toJSONString();

            } else {

                JSONObject obj = new JSONObject();
                obj.put("name", "not_exist");
                str = obj.toJSONString();
                User newUser = new User();
                newUser.setUserName(userName);
                newUser.setPassword(password);
                newUser.setRole(role);
                userDataManager.saveNewUser(newUser);

            }

        } else {

            JSONObject obj = new JSONObject();
            obj.put("name", "invalid_data");
            str = obj.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}