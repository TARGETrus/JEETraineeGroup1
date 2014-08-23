package servlets;

import DAOHandler.UserDataManager;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_user_servlet")
public class AJAXAdminEditUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDataManager userDataManager = new UserDataManager();

        String          userCurrName    = req.getParameter("curr_name");
        String          nameName        = req.getParameter("user");
        String          pass            = req.getParameter("old_pwd");
        String          newPass         = req.getParameter("new_pwd");
        String          role            = req.getParameter("role");

        String str = null;

        JSONObject obj = new JSONObject();
        obj.put("newName", userCurrName);

        if ((newPass.length() != 0 && pass.length() != 0 && pass.equals(new UserDataManager().getUserData(userCurrName).getPassword()))) {

            obj.put("name", "change successful!");
            userDataManager.changeUserPassword(userCurrName, newPass);

        }

        if (role.length() != 0) {

            obj.put("name", "change successful!");
            userDataManager.changeUserRole(userCurrName, role);

        }

        if (nameName.length() != 0) {

            obj.put("name", "change successful!");
            obj.put("newName", nameName);
            userDataManager.changeUserName(userCurrName, nameName);

        }


        if (nameName.length() == 0 && newPass.length() == 0 && role.length() == 0) {

            obj.put("name", "invalid_data!");

        }

        str = obj.toJSONString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}