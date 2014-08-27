package webAppAPI.xmlAPI;

import DAOHandler.*;
import webAppAPI.xmlModelReader.*;
import model.*;
import org.hibernate.HibernateException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import webAppAPI.xmlModelWriter.XMLTagNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * Servlet implementation class
 */
@WebServlet("/xmlAPI/UpdateEntity")
public class UpdateEntity extends HttpServlet {

    private static final long   serialVersionUID  = 1L;
    private static final String ERROR_MSG         = "Internal Error occupied while updating entity data!";
    private static final String ERROR_ENTITY_MSG  = "Wrong entity type!";

    private UserDataManager    userDataManager    = new UserDataManager();
    private EventDataManager   eventDataManager   = new EventDataManager();
    private GroupDataManager   groupDataManager   = new GroupDataManager();
    private FilterDataManager  filterDataManager  = new FilterDataManager();
    private CommentDataManager commentDataManager = new CommentDataManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String entityType = (String) request.getParameter("entity_type");

        SAXParserFactory factory = SAXParserFactory.newInstance();

        switch (entityType) {

            case XMLTagNames.user:
                try {

                    User user = null;

                    XMLUserReader userReader = new XMLUserReader();
                    SAXParser     saxParser  = factory.newSAXParser();
                    String        xmlInput   = request.getParameter("entity_xml");
                    //String        xmlInput   = "<Entity><user><userID>1</userID><userName>name</userName><password>password</password><role>admin</role><event><eventID>1</eventID><eventName>event2</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>1.10.2014</date><eventAdmin>name</eventAdmin></event><group><groupID>1</groupID><groupName>group2</groupName><groupAdmin>name</groupAdmin></group><filter><filterID>1</filterID><filterName>filter</filterName><filterData>asd:asd dsa:dsa</filterData><user><userID>1</userID><userName>name</userName><password>password</password><role>admin</role></user></filter></user></Entity>";
                    //<Entity><user><userName>name1</userName><password>password</password><role>admin</role><event><eventID>4</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>11.10.2014</date><eventAdmin>name1</eventAdmin></event><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group></user></Entity>

                    saxParser.parse(new InputSource(new StringReader(xmlInput)), userReader);
                    user = userReader.getUser();

                    userDataManager.modifyUser(user);

                    response.getWriter().print(user.toString());

                } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.event:
                try {

                    Event event = null;

                    XMLEventReader eventReader = new XMLEventReader();
                    SAXParser      saxParser   = factory.newSAXParser();
                    String         xmlInput    = request.getParameter("entity_xml");
                    //String         xmlInput    = "<Entity><event><eventID>2</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin><user><userID>2</userID><userName>name1</userName><password>password</password><role>regular</role></user><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group><comment><commentID>2</commentID><commentName>comment1</commentName><commentDescription>comment description</commentDescription><event><eventID>2</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin></event></comment></event></Entity>";
                    //<Entity><event><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group></event></Entity>

                    saxParser.parse(new InputSource(new StringReader(xmlInput)), eventReader);
                    event = eventReader.getEvent();

                    eventDataManager.modifyEvent(event);

                    response.getWriter().print(event.toString());

                } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.group:
                try {

                    Groupp group = null;

                    XMLGroupReader groupReader = new XMLGroupReader();
                    SAXParser      saxParser   = factory.newSAXParser();
                    String         xmlInput    = request.getParameter("entity_xml");
                    //String         xmlInput    = "<Entity><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin><user><userID>2</userID><userName>name1</userName><password>password</password><role>regular</role></user><event><eventID>2</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin></event></group></Entity>";
                    //<Entity><group><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group></Entity>

                    saxParser.parse(new InputSource(new StringReader(xmlInput)), groupReader);
                    group = groupReader.getGroup();

                    groupDataManager.modifyGroup(group);

                    response.getWriter().print(group.toString());

                } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.filter:
                try {

                    Filter filter = null;

                    XMLFilterReader filterReader = new XMLFilterReader();
                    SAXParser       saxParser    = factory.newSAXParser();
                    String          xmlInput     = request.getParameter("entity_xml");
                    //String          xmlInput     = "<Entity><filter><filterID>1</filterID><filterName>filter</filterName><filterData>asd:asd dsa:dsa</filterData><user><userID>1</userID><userName>name</userName><password>password</password><role>admin</role></user></filter></Entity>";
                    //<Entity><filter><filterName>filter1</filterName><filterData>asd:asd dsa:dsa</filterData><user><userID>2</userID><userName>name1</userName><password>password1</password><role>admin</role></user></filter></Entity>

                    saxParser.parse(new InputSource(new StringReader(xmlInput)), filterReader);
                    filter = filterReader.getFilter();

                    filterDataManager.modifyFilter(filter);

                    response.getWriter().print(filter.toString());

                } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            case XMLTagNames.comment:
                try {

                    Comment comment = null;

                    XMLCommentReader commentReader = new XMLCommentReader();
                    SAXParser        saxParser     = factory.newSAXParser();
                    String           xmlInput      = request.getParameter("entity_xml");
                    //String           xmlInput      = "<Entity><comment><commentID>1</commentID><commentName>comment</commentName><commentDescription>comment description</commentDescription><event><eventID>1</eventID><eventName>event2</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>1.10.2014</date><eventAdmin>name</eventAdmin></event></comment></Entity>";
                    //<Entity><comment><commentName>comment1</commentName><commentDescription>comment description</commentDescription><event><eventID>4</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>11.10.2014</date><eventAdmin>name1</eventAdmin></event></comment></Entity>

                    saxParser.parse(new InputSource(new StringReader(xmlInput)), commentReader);
                    comment = commentReader.getComment();

                    commentDataManager.modifyComment(comment);

                    response.getWriter().print(comment.toString());

                } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_MSG);

                }

                break;

            default:
                response.sendError(response.SC_INTERNAL_SERVER_ERROR, ERROR_ENTITY_MSG);

                break;

        }

    }

}