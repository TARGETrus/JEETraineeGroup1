package servlets;


import aDAOTest.TestManager;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class JSONRegServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("user");
        String str = null;
        if(!(name.length() == 0)){
            TestManager manager = new TestManager();
            if(manager.getUserLoginData(name).getUserName().equals(name)){

                JSONObject obj = new JSONObject();
                obj.put("name", "exist");
                str =  obj.toJSONString();

            }else{
                JSONObject obj = new JSONObject();
                obj.put("name", "not_exist");
                str = obj.toJSONString();
            }
        }else {
            JSONObject obj = new JSONObject();
            obj.put("name", "invalid_data");
            str = obj.toJSONString();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);


    }

}
