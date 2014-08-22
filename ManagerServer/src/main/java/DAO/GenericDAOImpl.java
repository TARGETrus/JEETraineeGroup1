package DAO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void merge(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public T findOne(Query query) {
        T one;
        one = (T) query.uniqueResult();
        return one;
    }

    @SuppressWarnings("unchecked")
    public List<T> findMany(Query query) {
        List<T> many;
        many = (List<T>) query.list();
        return many;
    }

    @SuppressWarnings("unchecked")
    public T findByID(Class clazz, int id) {
        T one = null;
        one = (T) sessionFactory.getCurrentSession().get(clazz, id);
        return one;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(Class clazz) {
        List<T> all = null;
        Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getName());
        all = (List<T>) query.list();
        return all;
    }

}