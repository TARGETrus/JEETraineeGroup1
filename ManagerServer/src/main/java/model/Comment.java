package model;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="comment_id", nullable=false, unique=true, length=12)
    private int    commentID;

    @Column(name="comment_name", length=255, nullable=false)
    private String commentName;

    @Column(name="comment", length=255, nullable=false)
    private String comment;

    @Column(name="user_name", length=255, nullable=false)
    private String userName;

    public Comment() {}

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}