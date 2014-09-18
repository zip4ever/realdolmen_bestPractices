package com.realdolmen.party.repositorie;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levifer on 15/09/2014.
 */
public abstract class AbstractRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> t;
    private List<T> tList = new ArrayList<>();

    public List<T> findAll() {
        Query query = entityManager.createQuery("SELECT t FROM " + returnEntityClass().getSimpleName() + " t");
        if (query.getResultList() == null) {
            return tList;
        }
        return query.getResultList();
    }

    public void persist(T t) {
        entityManager.persist(t);
    }

    public void remove(T t) {
        entityManager.remove(t);
    }

    public void flush() {
        entityManager.flush();
    }

    public void merge(T t) { entityManager.merge(t);}


    @SuppressWarnings("unchecked")
      public Class<T> returnEntityClass() {
      ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                           .getGenericSuperclass();
                return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
             }


    public EntityManager getEntityManager() {
        return entityManager;
    }
}
