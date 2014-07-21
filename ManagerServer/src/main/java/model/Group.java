package model;

import javax.persistence.*;

@Entity
@Table(name="group")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="group_id", nullable=false, unique=true, length=12)
    private int    groupID;

    @Column(name="group_name", length=255, nullable=false)
    private String groupName;

    public Group() {}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

}