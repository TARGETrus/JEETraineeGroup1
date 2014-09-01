package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    protected EntityManager entityManager;

    @Required
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void merge(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @SuppressWarnings("unchecked")
    public T findOne(Query query) {
        List<T> results = (List<T>) query.getResultList();
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findMany(Query query) {
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T findByID(Class clazz, int id) {
        return (T) entityManager.find(clazz, id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll(Class clazz) {
        Query query = entityManager.createQuery("from " + clazz.getName());
        return (List<T>) query.getResultList();
    }

}