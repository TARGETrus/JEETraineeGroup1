package DAO;

import model.Groupp;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAOImpl extends GenericDAOImpl<Groupp> implements GroupDAO {

    public Groupp getGroupData(String name) {

        Query query = entityManager.createQuery("from Groupp as groupp " +
                "where groupp.groupName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

    public Groupp getCompleteGroupData(String name) {

        Query query = entityManager.createQuery("from Groupp as groupp " +
                "left join fetch groupp.users as users " +
                "left join fetch groupp.events as events " +
                "where groupp.groupName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

    @SuppressWarnings("unchecked")
    public List<Groupp> findAll() {

        Query query = entityManager.createQuery("from Groupp as groupp " +
                "left join fetch groupp.users as users");

        return (List<Groupp>) query.getResultList();

    }

}