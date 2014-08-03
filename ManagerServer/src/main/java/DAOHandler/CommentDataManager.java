package DAOHandler;

import DAO.*;
import model.Comment;
import org.hibernate.HibernateException;

public class CommentDataManager {

    private CommentDAO commentDAO = new CommentDAOImpl();

    // Create foo
    public void saveNewComment(Comment comment) {

        try {

            HibernateUtil.beginTransaction();
            commentDAO.save(comment);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Get foo
    public Comment getCommentData(String name) {

        Comment comment = null;

        try {

            HibernateUtil.beginTransaction();
            comment = (Comment) commentDAO.getCommentData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return comment;

    }

}