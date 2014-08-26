package webAppAPI.create;

import DAOHandler.EventDataManager;
import XMLModelReader.XMLEventReader;
import model.Event;
import org.hibernate.HibernateException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {

    private static final long   serialVersionUID = 1L;
    private EventDataManager    eventDataManager = new EventDataManager();
    private static final String errormsg         = "Internal Error occupied, while sending a event";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        Event event = new Event();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            XMLEventReader eventReader = new XMLEventReader();
            SAXParser      saxParser   = factory.newSAXParser();
            String         xmlInput    = request.getParameter("event_xml");
            //String         xmlInput    = "<Entity><event><eventID>2</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin><user><userID>2</userID><userName>name1</userName><password>password</password><role>regular</role></user><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group><comment><commentID>2</commentID><commentName>comment1</commentName><commentDescription>comment description</commentDescription><event><eventID>2</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin></event></comment></event></Entity>";
            //<Entity><event><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group></event></Entity>

            saxParser.parse(new InputSource(new StringReader(xmlInput)), eventReader);
            event = eventReader.getEvent();

            eventDataManager.saveNewEvent(event);

            response.getWriter().print(event.toString());

        } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

            response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

        }

    }

}