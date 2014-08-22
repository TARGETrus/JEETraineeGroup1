package DAOHandler;

import DAO.*;
import model.Event;
import model.Filter;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilterDataManager {

    @Autowired
    private FilterDAO filterDAO;

    // Create foo
    @Transactional
    public void saveNewFilter(Filter filter) {

        try {

            filterDAO.save(filter);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Get foo
    @Transactional
    public Filter getFilterData(String name) {

        Filter filter = null;

        try {

            filter = (Filter) filterDAO.getFilterData(name);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return filter;

    }

    @Transactional
    public List<Filter> getAllFilters() {

        List<Filter> filter = null;

        try {

            filter = (List<Filter>) filterDAO.findAll(Filter.class);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

        return filter;

    }

    // Modify foo
    @Transactional
    public void modifyFilter(Filter filter) {

        try {

            filterDAO.merge(filter);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

    // Delete foo
    @Transactional
    public void deleteFilter(Filter filter) {

        try {

            filterDAO.delete(filter);

        } catch (HibernateException e) {

            System.out.println("Hibernate exception: " + e.getMessage());

        }

    }

}
