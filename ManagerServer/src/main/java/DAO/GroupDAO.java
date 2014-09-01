package DAO;

import model.Groupp;
import java.util.List;

public interface GroupDAO extends GenericDAO<Groupp> {

    public Groupp getGroupData(String name);

    public Groupp getCompleteGroupData(String name);

    public List<Groupp> findAll();

}