package servlets;


import DAOHandler.GroupDataManager;
import model.Group;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add_group")
public class AJAXAddGroup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupName = req.getParameter("groupName");
        String str = null;

        if(groupName.length() != 0){//TODO надо сделать уникальные имена,а если не надо тогда тут все готово
            GroupDataManager manager = new GroupDataManager();
            JSONObject obj = new JSONObject();
            obj.put("name", "add_group");
            str = obj.toJSONString();
            Group group = new Group();
            group.setGroupName(groupName);
            manager.saveNewGroup(group);
        } else {
            JSONObject obj = new JSONObject();
            obj.put("name", "error");
            str = obj.toJSONString();
        }


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
