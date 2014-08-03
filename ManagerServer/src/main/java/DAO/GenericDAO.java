package DAO;
import org.hibernate.Query;

import java.util.List;


public interface GenericDAO<T> {

    public void save(T entity);

    public void merge(T entity);

    public void delete(T entity);

    public T findOne(Query query);

    public List<T> findMany(Query query);

    public T findByID(Class clazz, int id);

    public List<T> findAll(Class clazz);

}