package servlets;

import DAOHandler.UserDataManager;
import model.Groupp;
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

@WebServlet("/groupjson")
public class AJAXGroupsJsonGen extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String json;
        JSONArray jsonarray = new JSONArray();

        if(username.length() != 0){

            ArrayList<Groupp> groups = new ArrayList<>(userDataManager.getUserCompleteData(username).getGroups());

            for (Groupp group : groups) {

                ArrayList<String> gusers = new ArrayList<>();
                JSONObject jsonGroup = new JSONObject();

                for (User user: group.getUsers()) {
                    gusers.add(user.getUserName());
                }

                jsonGroup.put("userlist", gusers);
                jsonGroup.put("groupname", group.getGroupName());
                jsonarray.add(jsonGroup);

            }

            json = jsonarray.toJSONString();

        } else {

            JSONObject obj = new JSONObject();
            obj.put("message", "error");
            json = obj.toJSONString();

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

}