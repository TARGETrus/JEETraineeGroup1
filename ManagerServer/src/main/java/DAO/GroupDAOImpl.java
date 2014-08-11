package DAO;

import model.Group;
import org.hibernate.Query;
import org.hibernate.Session;

public class GroupDAOImpl extends GenericDAOImpl<Group> implements GroupDAO {

    public Group getGroupData(String name) {

        Session hibernateSession = this.getSession();
        Query query = hibernateSession.createQuery("from Groupp where group_name= :name");
        query.setString("name", name);
        return findOne(query);

    }

    public Group getCompleteGroupData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Groupp as groupp " +
                "left join fetch groupp.users as users " +
                "left join fetch groupp.events as events " +
                "where groupp.groupName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}