package DAO;

import model.Group;

public interface GroupDAO extends GenericDAO<Group> {

    public Group getGroupData(String name);

    public Group getCompleteGroupData(String name);

}