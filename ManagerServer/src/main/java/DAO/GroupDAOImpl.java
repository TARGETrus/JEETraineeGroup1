package DAO;

import model.Group;
import org.hibernate.Query;
import org.hibernate.Session;

public class GroupDAOImpl extends GenericDAOImpl<Group> implements GroupDAO {

    public Group getGroupData(String name) {
        Session hibernateSession = this.getSession();
        Query query = hibernateSession.createQuery("from Group where group_name= :name");
        query.setString("name", name);
        return findOne(query);
    }

}