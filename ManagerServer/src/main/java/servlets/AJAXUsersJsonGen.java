package servlets;

import DAOHandler.UserDataManager;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/userjson")
public class AJAXUsersJsonGen extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = null;
        JSONArray jsonarray = new JSONArray();

        ArrayList<User> users = new ArrayList<>(userDataManager.getAllUsers());

        for (User user : users) {


            JSONObject jsonUser = new JSONObject();

            jsonUser.put("username", user.getUserName());
            jsonarray.add(jsonUser);

            json = jsonarray.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

}