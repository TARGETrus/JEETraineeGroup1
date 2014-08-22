package DAOHandler;

import DAO.*;
import model.Comment;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentDataManager {

    @Autowired
    private CommentDAO commentDAO;

    // Create foo
    @Transactional
    public void saveNewComment(Comment comment) {

        try {

            commentDAO.save(comment);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Get foo
    @Transactional
    public Comment getCommentData(String name) {

        Comment comment = null;

        try {

            comment = (Comment) commentDAO.getCommentData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return comment;

    }

    @Transactional
    public List<Comment> getAllComments() {

        List<Comment> comment = null;

        try {

            comment = (List<Comment>) commentDAO.findAll(Comment.class);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return comment;

    }

    // Modify foo
    @Transactional
    public void modifyComment(Comment comment) {

        try {

            commentDAO.merge(comment);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Delete foo
    @Transactional
    public void deleteComment(Comment comment) {

        try {

            commentDAO.delete(comment);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

}