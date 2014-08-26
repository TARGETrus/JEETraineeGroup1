package webAppAPI.create;

import DAOHandler.FilterDataManager;
import XMLModelReader.XMLFilterReader;
import model.Filter;
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
@WebServlet("/CreateFilter")
public class CreateFilter extends HttpServlet {

    private static final long   serialVersionUID  = 1L;
    private FilterDataManager   filterDataManager = new FilterDataManager();
    private static final String errormsg          = "Internal Error occupied, while sending a filter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        Filter filter = new Filter();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            XMLFilterReader filterReader = new XMLFilterReader();
            SAXParser       saxParser    = factory.newSAXParser();
            String          xmlInput     = request.getParameter("filter_xml");
            //String          xmlInput     = "<Entity><filter><filterID>1</filterID><filterName>filter</filterName><filterData>asd:asd dsa:dsa</filterData><user><userID>1</userID><userName>name</userName><password>password</password><role>admin</role></user></filter></Entity>";
            //<Entity><filter><filterName>filter1</filterName><filterData>asd:asd dsa:dsa</filterData><user><userID>2</userID><userName>name1</userName><password>password1</password><role>admin</role></user></filter></Entity>

            saxParser.parse(new InputSource(new StringReader(xmlInput)), filterReader);
            filter = filterReader.getFilter();

            filterDataManager.saveNewFilter(filter);

            response.getWriter().print(filter.toString());

        } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

            response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

        }

    }

}