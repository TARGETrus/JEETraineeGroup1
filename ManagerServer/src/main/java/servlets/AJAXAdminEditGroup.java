package servlets;

import DAOHandler.GroupDataManager;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit_group_servlet")
public class AJAXAdminEditGroup extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GroupDataManager groupDataManager = new GroupDataManager();

        String           groupCurrName    = req.getParameter("curr_name");
        String           groupName        = req.getParameter("group");
        String           group_admin      = req.getParameter("group_admin");

        String str = null;

        JSONObject obj = new JSONObject();
        obj.put("newName", groupCurrName);

        if (group_admin.length() != 0) {

            obj.put("name", "change successful!");
            groupDataManager.changeGroupAdmin(groupCurrName, group_admin);

        }

        if (groupName.length() != 0) {

            obj.put("name", "change successful!");
            obj.put("newName", groupName);
            groupDataManager.changeGroupName(groupCurrName, groupName);

        }

        if (groupName.length() == 0 && group_admin.length() == 0) {

            obj.put("name", "invalid_data!");

        }

        str = obj.toJSONString();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(str);

    }

}
