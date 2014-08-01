package xmlAPIServlets;


import model.Group;
import model.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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


    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String name = (String) request.getAttribute(XMLTagNames.user_userName);
    	
        //TO DO - Some operations with DAO like getLoginData(String name)
        User user = null;
        try {
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLUserWriter.write(out, user);
		} catch (XMLStreamException | FactoryConfigurationError e) {
			response.getWriter().write("<error>");
		}


    }

}