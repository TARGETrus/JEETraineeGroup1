package DAO;

import model.Comment;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

@Repository
public class CommentDAOImpl extends GenericDAOImpl<Comment> implements CommentDAO {

    public Comment getCommentData(String name) {

        Query query = entityManager.createQuery("from Comment as comment " +
                "left join fetch comment.event " +
                "where comment.commentName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

}