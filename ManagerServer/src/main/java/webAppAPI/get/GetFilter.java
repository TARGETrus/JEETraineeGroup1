package webAppAPI.get;

import DAOHandler.FilterDataManager;
import model.Filter;
import xmlModelWriter.XMLFilterWriter;
import xmlModelWriter.XMLTagNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/GetFilter")
public class GetFilter extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FilterDataManager filterDataManager = new FilterDataManager();
    private static final String errormsg = "Internal Error occupied, while recieving a filter";

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String name = (String) request.getParameter(XMLTagNames.filter_filterName);

        try {

            Filter filter = filterDataManager.getFilterData(name);
            XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
            XMLFilterWriter.write(out, filter);

        } catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

            response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

        }

    }

}