package xmlAPIServlets;

import DAOHandler.UserDataManager;
import model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import xmlModelWriter.XMLTagNames;
import xmlModelWriter.XMLUserWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/GetUser")
public class GetUser extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDataManager userDataManager = new UserDataManager();

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String name = (String) request.getAttribute(XMLTagNames.user_userName);

        User user = userDataManager.getUserCompleteData(name);

        try {
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLUserWriter.write(out, user);
		} catch (XMLStreamException | FactoryConfigurationError e) {
			response.getWriter().write("<error>");
		}


    }

}