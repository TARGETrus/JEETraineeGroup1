package model;

import javax.persistence.*;

@Entity
@Table(name="comments", schema="web_app_db")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="comment_id", nullable=false, unique=true, length=12)
    private int    commentID;

    @Column(name="comment_name", length=255, nullable=false)
    private String commentName;//del

    @Column(name="comment", length=255, nullable=false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString(){

        return "\nComment data: \n" + "ID: " + commentID + ", Title: " + commentName +
                ", Content: " + comment + event.getEventID() + "\n";

    }

}