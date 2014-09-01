package DAO;

import model.Filter;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FilterDAOImpl extends GenericDAOImpl<Filter> implements FilterDAO {

    public Filter getFilterData(String name) {

        Query query = entityManager.createQuery("from Filter as filter " +
                "left join fetch filter.user " +
                "where filter.filterName = :name");
        query.setParameter("name", name);

        return findOne(query);

    }

}