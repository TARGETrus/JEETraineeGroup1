package servlets;


import aDAOTest.TestManager;
import model.User;

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
        TestManager manager = new TestManager();
        User user = manager.getUserLoginData(name);
        if(!user.getUserName().equals(null)){
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
//        JSONObject jsonObj = new JSONObject(user);
//        String str = jsonObj.toString();
            resp.getWriter().write("OK!" + user.getUserName());
        }else{
            resp.getWriter().write("NOPE!");
        }

    }

}
