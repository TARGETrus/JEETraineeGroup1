package DAO;

import model.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class FilterDAOImpl extends GenericDAOImpl<Filter> implements FilterDAO {

    public Filter getFilterData(String name) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Filter as filter " +
                "where filter.filterName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}