package DAO;

import model.Groupp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupDAOImpl extends GenericDAOImpl<Groupp> implements GroupDAO {

    public Groupp getGroupData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Groupp as groupp " +
                "where groupp.groupName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public Groupp getCompleteGroupData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Groupp as groupp " +
                "left join groupp.users as users " +
                "left join fetch groupp.events as events " +
                "where groupp.groupName = :name");
        query.setString("name", name);

        return findOne(query);

    }

    public List<Groupp> findAll() {

        List<Groupp> all = null;
        Query query = sessionFactory.getCurrentSession().createQuery("from Groupp as groupp " +
                "left join fetch groupp.users as users");
        all = (List<Groupp>) query.list();

        return all;

    }

}