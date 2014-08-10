package servlets;


import DAOHandler.UserDataManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Groupp;
import model.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/jsongen")
public class AJAXJsonGen extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        UserDataManager manager = new UserDataManager();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json;
        if(username.length() != 0){
            User user = manager.getUserCompleteData(username);
            Set<Groupp> groups = user.getGroups();
            json = gson.toJson(groups);//TODO не хочет отдавать мне группы
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }else {
            JSONObject obj = new JSONObject();
            obj.put("message", "error");
            json = obj.toJSONString();
        }


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }
}
