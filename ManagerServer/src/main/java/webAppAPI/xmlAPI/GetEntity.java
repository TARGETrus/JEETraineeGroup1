package webAppAPI.xmlAPI;

import DAOHandler.*;
import model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import servlets.AbstractAutowiringServlet;
import webAppAPI.xmlModelWriter.*;

/**
 * Servlet implementation class
 */
@WebServlet("/xmlAPI/GetEntity")
public class GetEntity extends AbstractAutowiringServlet {

    private static final long   serialVersionUID  = 1L;
    private static final String ERROR_MSG         = "Internal Error occupied while receiving entity data!";
    private static final String ERROR_ENTITY_MSG  = "Wrong entity type!";

    @Autowired
    private UserDataManager userDataManager;

    @Autowired
    private EventDataManager eventDataManager;

    @Autowired
    private GroupDataManager groupDataManager;

    @Autowired
    private FilterDataManager filterDataManager;

    @Autowired
    private CommentDataManager commentDataManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	String entityType = (String) request.getParameter("entity_type");
        String entityName = (String) request.getParameter("entity_name");

        switch (entityType) {

            case XMLTagNames.user :
                try {

                	User user = userDataManager.getUserCompleteData(entityName);
		        	XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
		        	XMLUserWriter.write(out, user);

		        } catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

		        	response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

		        }

                break;

            case XMLTagNames.event :
                try {

                    Event event = eventDataManager.getEventCompleteData(entityName);
                    XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
                    XMLEventWriter.write(out, event);

                } catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.group :
                try {

                    Groupp group = groupDataManager.getGroupCompleteData(entityName);
                    XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
                    XMLGroupWriter.write(out, group);

                } catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.filter :
                try {

                    Filter filter = filterDataManager.getFilterData(entityName);
                    XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
                    XMLFilterWriter.write(out, filter);

                } catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.comment :
                try {

                    Comment comment = commentDataManager.getCommentData(entityName);
                    XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
                    XMLCommentWriter.write(out, comment);

                } catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            default:
                response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_ENTITY_MSG);

                break;

        }

    }

}