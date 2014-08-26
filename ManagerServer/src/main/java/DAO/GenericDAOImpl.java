package DAO;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.save(entity);
    }

    public void update(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.update(entity);
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