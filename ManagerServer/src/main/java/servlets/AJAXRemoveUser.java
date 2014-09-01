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

@WebServlet("/delete_user")
public class AJAXRemoveUser extends AbstractAutowiringServlet {

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
        String str = null;

        if (!userName.equals("")) {

            User user = userDataManager.getUserData(userName);

            if (user != null) {

                JSONObject obj = new JSONObject();
                obj.put("name", "removed");
                str = obj.toJSONString();
                userDataManager.deleteUser(user);

            } else {

                JSONObject obj = new JSONObject();
                obj.put("name", "not_exist");

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