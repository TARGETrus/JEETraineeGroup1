package servlets;

import aDAOTest.TestManager;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String userID = "leo";
    private final String password = "pass";

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        List<User> users = new TestManager().findAllUsers();


        // get request parameters for userID and password
        String username = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if(users.size() != 0){
            for(User user : users){
                if(user.getUserName().equals(username) && user.getPassword().equals(pwd)){
                    Cookie loginCookie = new Cookie("username",username);
                    //setting cookie to expiry in 30 mins
                    loginCookie.setMaxAge(30*60);
                    response.addCookie(loginCookie);
                    response.sendRedirect("index.jsp");
                    break;
                }else{
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out= response.getWriter();
                    out.println("<font color=red>Either username name or password is wrong.</font>");
                    rd.include(request, response);
                }
            }
        }

//        if(userID.equals(username) && password.equals(pwd)){
//            Cookie loginCookie = new Cookie("username",username);
//            //setting cookie to expiry in 30 mins
//            loginCookie.setMaxAge(30*60);
//            response.addCookie(loginCookie);
//            response.sendRedirect("index.jsp");
//        }else{
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
//            PrintWriter out= response.getWriter();
//            out.println("<font color=red>Either username name or password is wrong.</font>");
//            rd.include(request, response);
//        }

    }

}