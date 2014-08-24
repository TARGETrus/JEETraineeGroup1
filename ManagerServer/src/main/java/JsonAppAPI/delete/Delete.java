package JsonAppAPI.delete;



import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/json/delete_date")
public class Delete extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action){
            case "delet_user_from_group":{
                break;
            }
            case "delet_user_from_event":{
                break;
            }
            case "delet_user":{
                break;
            }
            case "delet_event":{
                break;
            }
            case "delet_group":{
                break;
            }
            default:{
                break;
            }
        }

    }


}
