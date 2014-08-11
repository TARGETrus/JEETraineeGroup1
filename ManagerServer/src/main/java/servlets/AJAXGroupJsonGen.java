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

@WebServlet("/jsongen")
public class AJAXGroupJsonGen extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        UserDataManager manager = new UserDataManager();
        String json;
        JSONArray jsonarray = new JSONArray();
        if(username.length() != 0){

            ArrayList<Groupp> groupps = new ArrayList<>(manager.getUserCompleteData(username).getGroupps());
            for(Groupp groupp : groupps){
                ArrayList<String> gusers = new ArrayList<>();
                JSONObject jsonGroup = new JSONObject();
                for(User user: groupp.getUsers()){
                    gusers.add(user.getUserName());
                }
                jsonGroup.put("userlist", gusers);
                jsonGroup.put("groupname", groupp.getGroupName());
                jsonarray.add(jsonGroup);

            }
            json = jsonarray.toJSONString();

        }else {
            JSONObject obj = new JSONObject();
            obj.put("message", "error");
            json = obj.toJSONString();
        }


//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        resp.getWriter().write(json);
        resp.setContentType("text/html;UTF-8");
        resp.getWriter().write(json);

    }
}
