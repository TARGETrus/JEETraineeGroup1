package DAO;

import model.Comment;

public interface CommentDAO extends GenericDAO<Comment> {

    public Comment getCommentData(String name);

}