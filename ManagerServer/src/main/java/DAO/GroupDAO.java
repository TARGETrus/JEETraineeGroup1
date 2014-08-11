package DAO;

import model.Groupp;

public interface GroupDAO extends GenericDAO<Groupp> {

    public Groupp getGroupData(String name);

    public Groupp getCompleteGroupData(String name);

}