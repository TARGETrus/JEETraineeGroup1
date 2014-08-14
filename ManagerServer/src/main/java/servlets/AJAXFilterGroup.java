package servlets;


import DAOHandler.GroupDataManager;
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

@WebServlet("/filtergroup")
public class AJAXFilterGroup extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String groupName = req.getParameter("groupName");
        GroupDataManager manager = new GroupDataManager();
        String json;
        JSONArray jsonarray = new JSONArray();
        if(username.length() != 0 && groupName.length() != 0){

            ArrayList<Groupp> groups = new ArrayList<>(manager.getAllGroups());
            for(Groupp group: groups){
                if(group.getGroupName().startsWith(groupName)){
                    ArrayList<String> gusers = new ArrayList<>();
                    JSONObject jsonGroup = new JSONObject();
                    for(User user: group.getUsers()){
                        gusers.add(user.getUserName());
                    }
                    jsonGroup.put("userlist", gusers);
                    jsonGroup.put("groupname", group.getGroupName());
                    jsonarray.add(jsonGroup);
                }
            }
            json = jsonarray.toJSONString();

        }else{
            ArrayList<Groupp> groups = new ArrayList<>(manager.getAllGroups());
            for(Groupp group: groups){
                ArrayList<String> gusers = new ArrayList<>();
                JSONObject jsonGroup = new JSONObject();
                for(User user: group.getUsers()){
                    gusers.add(user.getUserName());
                }
                jsonGroup.put("userlist", gusers);
                jsonGroup.put("groupname", group.getGroupName());
                jsonarray.add(jsonGroup);

            }
            json = jsonarray.toJSONString();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }
}
