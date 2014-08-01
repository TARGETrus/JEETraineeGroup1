package xmlAPIServlets;


import model.Comment;


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

import xmlModelWriter.XMLCommentWriter;
import xmlModelWriter.XMLTagNames;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/GetComment")
public class GetComment extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	String name = (String) request.getAttribute(XMLTagNames.comment_commentName);
    	
    	
    	//TO DO - Some operations with DAO
    	Comment comment = null;
        try {
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLCommentWriter.write(out, comment);
		} catch (XMLStreamException | FactoryConfigurationError e) {
			response.getWriter().write("<error>");
		}


    }

}