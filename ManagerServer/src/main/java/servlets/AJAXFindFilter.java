package servlets;

import DAOHandler.FilterDataManager;
import model.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findfilter")
public class AJAXFindFilter extends AbstractAutowiringServlet {

    @Autowired
    private FilterDataManager filterDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filtername = req.getParameter("filtername");
        Filter filter =  filterDataManager.getFilterData(filtername);

        String json = filter.getFilterData();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}