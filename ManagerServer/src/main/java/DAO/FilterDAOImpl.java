package DAO;

import model.Filter;
import org.hibernate.Query;
import org.hibernate.Session;

public class FilterDAOImpl extends GenericDAOImpl<Filter> implements FilterDAO {

    public Filter getFilterData(String name) {

        Session hibernateSession = this.getSession();

        Query query = hibernateSession.createQuery("from Filter as filter " +
                "where filter.filterName = :name");
        query.setString("name", name);

        return findOne(query);

    }

}