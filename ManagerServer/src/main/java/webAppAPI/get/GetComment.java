package webAppAPI.get;

import DAOHandler.CommentDataManager;
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
 * Servlet implementation class
 */
@WebServlet("/GetComment")
public class GetComment extends HttpServlet {

    private static final long   serialVersionUID   = 1L;
    private CommentDataManager  commentDataManager = new CommentDataManager();
    private static final String errormsg           = "Internal Error occupied, while receiving a comment";

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	String name = (String) request.getParameter(XMLTagNames.comment_commentName);

        try {

        	Comment comment = commentDataManager.getCommentData(name);
			XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(response.getWriter());
			XMLCommentWriter.write(out, comment);

		} catch (XMLStreamException | FactoryConfigurationError | NullPointerException e) {

			response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

		}

    }

}