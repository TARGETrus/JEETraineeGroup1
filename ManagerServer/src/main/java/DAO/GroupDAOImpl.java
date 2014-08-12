package DAO;

import model.Groupp;
import org.hibernate.Query;
import org.hibernate.Session;

public class GroupDAOImpl extends GenericDAOImpl<Groupp> implements GroupDAO {

    public Groupp getGroupData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Groupp as groupp " +
                "where groupp.groupName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public Groupp getCompleteGroupData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Groupp as groupp " +
                "left join fetch groupp.users as users " +
                "left join fetch groupp.events as events " +
                "where groupp.groupName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}