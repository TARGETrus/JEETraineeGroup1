package servlets;


import DAOHandler.UserDataManager;
import model.Groupp;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/userjson")
public class AJAXUsersJsonGen extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDataManager manager = new UserDataManager();
        String json = null;
        JSONArray jsonarray = new JSONArray();

        ArrayList<User> users = new ArrayList<>(manager.getAllUsers());

        for(User user : users){


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
