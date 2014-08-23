package DAO;

import model.Groupp;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

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

    public List<Groupp> findAll() {

        Session hibernateSession = this.getSession();

        List<Groupp> all = null;
        Query query = hibernateSession.createQuery("from Groupp as groupp " +
                "left join fetch groupp.users as users");
        all = (List<Groupp>) query.list();

        return all;

    }

}