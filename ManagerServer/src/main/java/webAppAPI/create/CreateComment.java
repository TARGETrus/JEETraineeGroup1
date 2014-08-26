package webAppAPI.create;

import DAOHandler.CommentDataManager;
import XMLModelReader.XMLCommentReader;
import model.Comment;
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
@WebServlet("/CreateComment")
public class CreateComment extends HttpServlet {

    private static final long   serialVersionUID   = 1L;
    private CommentDataManager  commentDataManager = new CommentDataManager();
    private static final String errormsg           = "Internal Error occupied, while sending a comment";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        Comment comment = new Comment();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            XMLCommentReader commentReader = new XMLCommentReader();
            SAXParser        saxParser     = factory.newSAXParser();
            String           xmlInput      = request.getParameter("comment_xml");
            //String           xmlInput      = "<Entity><comment><commentID>1</commentID><commentName>comment</commentName><commentDescription>comment description</commentDescription><event><eventID>1</eventID><eventName>event2</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>1.10.2014</date><eventAdmin>name</eventAdmin></event></comment></Entity>";
            //<Entity><comment><commentName>comment1</commentName><commentDescription>comment description</commentDescription><event><eventID>4</eventID><eventName>event1</eventName><coordinates>1444:2111</coordinates><latitude>100.0</latitude><longitude>100.0</longitude><date>11.10.2014</date><eventAdmin>name1</eventAdmin></event></comment></Entity>

            saxParser.parse(new InputSource(new StringReader(xmlInput)), commentReader);
            comment = commentReader.getComment();

            commentDataManager.saveNewComment(comment);

            response.getWriter().print(comment.toString());

        } catch (ParserConfigurationException | SAXException | HibernateException | NullPointerException e) {

            response.sendError(response.SC_INTERNAL_SERVER_ERROR, errormsg);

        }

    }

}