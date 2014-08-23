package servlets;


import DAOHandler.FilterDataManager;
import DAOHandler.UserDataManager;
import model.Filter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addfilter")
public class AJAXAddUserFilters extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        UserDataManager userDataManager = new UserDataManager();
        String username = req.getParameter("username");
        String flag = req.getParameter("flag");

        switch (flag){
            case "apply":{
                String json;
                JSONArray jsonarray = new JSONArray();
                if(username.length() != 0){
                    ArrayList<Filter> filters = new ArrayList<>(userDataManager.getUserCompleteData(username).getFilters());
                    for(Filter filter: filters){
                        jsonarray.add(filter.getFilterData());
                    }
                    json = jsonarray.toJSONString();

                }else{
                    JSONObject obj = new JSONObject();
                    obj.put("message", "error");
                    json = obj.toJSONString();
                }


                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
                break;
            }

            case "save":{
                String eventFilter = req.getParameter("eventFilter");
                String radius = req.getParameter("radius");
                String userFilter = req.getParameter("userFilter");
                String point = req.getParameter("point");
                String lat = req.getParameter("lat");
                String lng = req.getParameter("lng");

                JSONObject object = new JSONObject();
                object.put("eventname", eventFilter);
                object.put("radius", radius);
                object.put("username", userFilter);
                object.put("point", point);
                object.put("lat", lat);
                object.put("lng", lng);

                Filter filter = new Filter();
                filter.setFilterData(object.toJSONString());
                filter.setFilterName("point:" + point + ", user:" + userFilter + ", event: " +
                eventFilter + ", radius: " + radius);
                filter.setUser(userDataManager.getUserCompleteData(username));//TODO типо добавление юзера
                FilterDataManager filterDataManager = new FilterDataManager();
                filterDataManager.saveNewFilter(filter);
                JSONObject respObject = new JSONObject();
                respObject.put("status", "OK");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(respObject.toJSONString());
                break;
            }
        }


    }
}
