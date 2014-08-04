package xmlAPIServlets;

import DAOHandler.EventDataManager;
import model.Event;

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

import xmlModelWriter.XMLEventWriter;
import xmlModelWriter.XMLTagNames;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/GetEvent")
public class GetEvent extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EventDataManager eventDataManager = new EventDataManager();
    private static final String errormsg = "Internal Error occupied, while recieving an event";

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String name = (String) request.getParameter(XMLTagNames.comment_commentName);

       
        try {
        	Event event = eventDataManager.getEventCompleteData(name);
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLEventWriter.write(out, event);
		} catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {
			response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);
		}


    }

}