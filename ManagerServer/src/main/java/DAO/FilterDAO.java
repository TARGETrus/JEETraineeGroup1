package DAO;

import model.Filter;

public interface FilterDAO extends GenericDAO<Filter> {

    public Filter getFilterData(String name);

}