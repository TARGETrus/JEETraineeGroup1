package xmlAPIServlets;


import model.Group;

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

import xmlModelWriter.XMLGroupWriter;
import xmlModelWriter.XMLTagNames;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/GetGroup")
public class GetGroup extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String name = (String) request.getAttribute(XMLTagNames.group_groupName);
    	
    	//TO DO - Some operations with DAO
    	Group group = null;
        try {
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLGroupWriter.write(out, group);
		} catch (XMLStreamException | FactoryConfigurationError e) {
			response.getWriter().write("<error>");
		}


    }

}