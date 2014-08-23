package DAO;

import model.Comment;
import org.hibernate.Query;
import org.hibernate.Session;

public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO {

    public Comment getCommentData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Comment as comment " +
                "where comment.commentName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}