package webAppAPI.create;

import DAOHandler.UserDataManager;
import XMLModelReader.XMLUserReader;
import model.User;
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
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {

    private static final long   serialVersionUID = 1L;
    private UserDataManager     userDataManager  = new UserDataManager();
    private static final String errormsg         = "Internal Error occupied, while sending a user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        User user = new User();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            XMLUserReader userReader = new XMLUserReader();
            SAXParser     saxParser  = factory.newSAXParser();
            String        xmlInput   = request.getParameter("user_xml");
            //String        xmlInput   = "<Entity><user><userID>1</userID><userName>name</userName><password>password</password><role>admin</role><event><eventID>1</eventID><eventName>event2</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>1.10.2014</date><eventAdmin>name</eventAdmin></event><group><groupID>1</groupID><groupName>group2</groupName><groupAdmin>name</groupAdmin></group><filter><filterID>1</filterID><filterName>filter</filterName><filterData>asd:asd dsa:dsa</filterData><user><userID>1</userID><userName>name</userName><password>password</password><role>admin</role></user></filter></user></Entity>";
            //<Entity><user><userName>name1</userName><password>password</password><role>admin</role><event><eventID>4</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>11.10.2014</date><eventAdmin>name1</eventAdmin></event><group><groupID>2</groupID><groupName>group1</groupName><groupAdmin>name1</groupAdmin></group></user></Entity>

            saxParser.parse(new InputSource(new StringReader(xmlInput)), userReader);
            user = userReader.getUser();

            userDataManager.saveNewUser(user);

            response.getWriter().print(user.toString());

        } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

            response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

        }

    }

}