package webAppAPI.update;

import DAOHandler.GroupDataManager;
import XMLModelReader.XMLGroupReader;
import model.Groupp;
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
@WebServlet("/UpdateGroup")
public class UpdateGroup extends HttpServlet {

    private static final long   serialVersionUID = 1L;
    private GroupDataManager    groupDataManager = new GroupDataManager();
    private static final String errormsg         = "Internal Error occupied, while sending a group";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        Groupp group = new Groupp();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            XMLGroupReader groupReader = new XMLGroupReader();
            SAXParser      saxParser   = factory.newSAXParser();
            String         xmlInput    = request.getParameter("group_xml");
            //String         xmlInput    = "<Entity><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin><user><userID>2</userID><userName>name1</userName><password>password</password><role>regular</role></user><event><eventID>2</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>1100.0</latitude><longitude>1100.0</longitude><date>1.10.2014</date><eventAdmin>name1</eventAdmin></event></group></Entity>";

            saxParser.parse(new InputSource(new StringReader(xmlInput)), groupReader);
            group = groupReader.getGroup();

            groupDataManager.modifyGroup(group);

            response.getWriter().print(group.toString());

        } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

            response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

        }

    }

}