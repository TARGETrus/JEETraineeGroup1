package servlets;

import DAOHandler.EventDataManager;
import DAOHandler.GroupDataManager;
import model.Event;

import model.Groupp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin_group_json")
public class AJAXAdminGetGroups extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GroupDataManager manager = new GroupDataManager();
        String json = null;
        JSONArray jsonarray = new JSONArray();

        ArrayList<Groupp> groups = new ArrayList<>(manager.getAllGroups());

        for(Groupp group : groups){


            JSONObject jsonUser = new JSONObject();

            jsonUser.put("groupname", group.getGroupName());
            jsonarray.add(jsonUser);

            json = jsonarray.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }

}