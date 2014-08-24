package JsonAppAPI.get;

import DAOHandler.UserDataManager;
import JsonAppAPI.jsonustil.JsonEventModel;
import JsonAppAPI.jsonustil.JsonGroupModel;
import JsonAppAPI.jsonustil.JsonUserModel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/json/get_date")
public class GetDate extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action){
            case "userlist":{
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new JsonUserModel().getJsonUserlist());
                break;
            }
            case "eventlist":{

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new JsonEventModel().getJsonEventlist());
                break;
            }
            case "grouplist":{

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new JsonGroupModel().getJsonGrouplist());
                break;
            }
            case "user_date":{
                String username = req.getParameter("username");
                String pass = req.getParameter("password");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new JsonUserModel().getJsonUser(username, pass));
                break;
            }
            case "event_date":{
                String eventname = req.getParameter("eventname");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new JsonEventModel().getJsonEvent(eventname));
                break;
            }
            case "groupdate":{
                String groupname = req.getParameter("groupname");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new JsonGroupModel().getJsonGroup(groupname));
                break;
            }
            default:{
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                JSONObject object = new JSONObject();
                JSONArray array = new JSONArray();
                object.put("error", "incorrect data");
                object.put("status", "error");
                array.put(object);
                resp.getWriter().write(array.toString());
                break;
            }
        }
    }

}
