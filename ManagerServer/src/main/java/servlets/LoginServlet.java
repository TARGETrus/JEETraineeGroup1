package servlets;

import DAOHandler.UserDataManager;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends AbstractAutowiringServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserDataManager userDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");

        User user = userDataManager.getUserData(username);
                
        if (user != null && password.equals(user.getPassword())) {
        	
        	//Hibernate Error :  failed to lazily initialize a collection
        	//Error might occur, because of session being closed
            user = userDataManager.getUserCompleteData(username);
            request.getSession().setAttribute("user", user);
            
            Cookie loginCookie = new Cookie("username",username);
            //setting cookie to expiry in 30 mins
            loginCookie.setMaxAge(30*60);
            response.addCookie(loginCookie);
            response.sendRedirect("main.jsp");

        } else {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red >Either username name or password is wrong.</font>");
            rd.include(request, response);

        }

    }

}