package webAppAPI.get;

import DAOHandler.GroupDataManager;
import model.Groupp;

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
    private GroupDataManager groupDataManager = new GroupDataManager();
    private static final String errormsg = "Internal Error occupied, while recieving a group";

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	String name = (String) request.getParameter(XMLTagNames.comment_commentName);

        try {

        	Groupp group = groupDataManager.getGroupCompleteData(name);
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLGroupWriter.write(out, group);

		} catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

			response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

		}

    }

}