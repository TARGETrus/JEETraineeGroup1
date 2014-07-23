package DAO;

import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }

    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }

    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }

    public T findOne(Query query) {
        T one;
        one = (T) query.uniqueResult();
        return one;
    }

    public List<T> findMany(Query query) {
        List<T> many;
        many = (List<T>) query.list();
        return many;
    }

    public T findByID(Class clazz, int id) {
        Session hibernateSession = this.getSession();
        T one = null;
        one = (T) hibernateSession.get(clazz, id);
        return one;
    }

    public List<T> findAll(Class clazz) {
        Session hibernateSession = this.getSession();
        List<T> all = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        all = (List<T>) query.list();
        return all;
    }

}