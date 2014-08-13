package DAOHandler;

import DAO.*;
import model.Event;
import model.Filter;
import org.hibernate.HibernateException;

import java.util.List;

public class FilterDataManager {

    private FilterDAO filterDAO = new FilterDAOImpl();
    private EventDAO eventDAO = new EventDAOImpl();

    // Create foo
    public void saveNewFilter(Filter filter) {

        try {

            HibernateUtil.beginTransaction();
            filterDAO.save(filter);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

    // Get foo
    public Filter getFilterData(String name) {

        Filter filter = null;

        try {

            HibernateUtil.beginTransaction();
            filter = (Filter) filterDAO.getFilterData(name);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return filter;

    }

    public List<Filter> getAllFilters() {

        List<Filter> filter = null;

        try {

            HibernateUtil.beginTransaction();
            filter = (List<Filter>) filterDAO.findAll(Filter.class);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return filter;

    }

    // Modify foo
    public void modifyFilter(Filter filter) {

        try {

            HibernateUtil.beginTransaction();
            filterDAO.merge(filter);
            HibernateUtil.commitTransaction();

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());
            HibernateUtil.rollbackTransaction();

        }

    }

}
