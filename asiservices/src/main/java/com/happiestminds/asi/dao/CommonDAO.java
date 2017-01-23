package com.happiestminds.asi.dao;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

/**
 * 
 * @author shruti.mishra
 *
 * @param <T>
 */
public interface CommonDAO<T> {

    //List<User> retrieveAccount();
    T findOne(final Long id);

    List< T> findAll();

    T save(final T entity);

    void update(final T entity);

    void merge(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);

    List<T> findByCriteria(FetchEntitities fetch, Criterion... criterion);

    List<T> findByCriteria(Criterion... criterion);
    
    List<T> findByCriteria(Map<String, String> aliases, Criterion... criterion);
}
