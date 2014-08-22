package DAO;

import model.Comment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO {

    public Comment getCommentData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Comment as comment " +
                "where comment.commentName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}