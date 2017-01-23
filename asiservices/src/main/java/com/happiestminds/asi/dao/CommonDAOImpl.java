package com.happiestminds.asi.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.happiestminds.asi.domain.DataResult;

/**
 * 
 * @author shruti.mishra
 *
 * @param <T>
 */
public abstract class CommonDAOImpl<T> implements CommonDAO<T> {

	private Class<T> clazz;
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public void setClazz(final Class< T> clazzToSet) {
        //System.out.println("sessionFactor.." + this.sessionFactory);
        // clazz = clazzToSet;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public T findOne(final Long id) {
        //Preconditions.checkArgument( id != null );
        return (T) getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List< T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @SuppressWarnings("unchecked")
    public List< T> findAllPage(int pageNumber) {
        Query q = getCurrentSession().createQuery("from " + clazz.getName());
        q.setFirstResult((pageNumber - 1) * 20);
        q.setMaxResults(20);
        return q.list();
    }
    //criteria.setFirstResult(();

    public T save(final T entity) {
        //Preconditions.checkNotNull( entity );			  
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public void update(final T entity) {
        //Preconditions.checkNotNull( entity );
        getCurrentSession().merge(entity);
    }

    public void merge(final T entity) {
        //Preconditions.checkNotNull( entity );
        getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        //Preconditions.checkNotNull( entity );
        getCurrentSession().delete(entity);
    }

    public void deleteById(final Long entityId) {
        final T entity = findOne(entityId);
        //Preconditions.checkState( entity != null );
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        for (Criterion c : criterion) {
            crit.add(c);
        }

        // genres
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int start, int max) {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        crit.setFirstResult(start);
        crit.setMaxResults(max);
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int start, int max, Order order, FetchEntitities fetch, Criterion... criterion) {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        if (start != -1) {
            crit.setFirstResult(start);
        }
        if (max != -1) {
            crit.setMaxResults(max);
        }
        if (order != null) {
            crit.addOrder(order);
        }
        if (fetch != null) {
            Map<String, FetchMode> fetchM = fetch.getFetchModeMap();
            if (fetchM != null) {
                Set<String> keys = fetchM.keySet();
                for (String k : keys) {
                    crit.setFetchMode(k, fetchM.get(k));
                }
            }
        }
        for (Criterion c : criterion) {
            crit.add(c);
        }
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        // genres
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(int pageNumber, Criterion... criterion) {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        crit.setFirstResult((pageNumber - 1) * 20);
        crit.setMaxResults(20);
        // genres
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public DataResult<T> findDataResultByCriteria(int pageNumber, Order order, Criterion... criterion) {
        DataResult<T> rs = new DataResult<T>();
        rs.setCount(((Long) getCurrentSession().createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult()).intValue());
        Criteria crit = getCurrentSession().createCriteria(clazz);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        if (order != null) {
            crit.addOrder(order);
        }
        crit.setFirstResult((pageNumber - 1) * 20);
        crit.setMaxResults(20);
        // genres
        rs.setCurrentPage(pageNumber);
        rs.setResults(crit.list());
        return rs;
        // return crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(FetchEntitities fetch, Criterion... criterion) {

        Criteria crit = getCurrentSession().createCriteria(clazz);
        Map<String, FetchMode> fetchM = fetch.getFetchModeMap();
        if (fetchM != null) {
            Set<String> keys = fetchM.keySet();
            for (String k : keys) {
                crit.setFetchMode(k, fetchM.get(k));
            }
        }
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public DataResult<T> findDataResultByCriteria(int start, int max, Order order, FetchEntitities fetch, Criterion... criterion) {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        Criteria criteria = getCurrentSession().createCriteria(clazz);
        DataResult<T> rs = new DataResult<T>();
        if (fetch != null) {
            Map<String, FetchMode> fetchM = fetch.getFetchModeMap();
            if (fetchM != null) {
                Set<String> keys = fetchM.keySet();
                for (String k : keys) {
                    crit.setFetchMode(k, fetchM.get(k));
                    criteria.setFetchMode(k, fetchM.get(k));
                }
            }
        }
        for (Criterion c : criterion) {
            crit.add(c);
            criteria.add(c);
        }
        String count = (criteria.setProjection(Projections.rowCount()).uniqueResult()).toString();
        rs.setCount(Integer.parseInt(count));
        if (start != -1) {
            crit.setFirstResult(start);
        }
        if (max != -1) {
            crit.setMaxResults(max);
        }
        if (order != null) {
            crit.addOrder(order);
        }
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        rs.setResults(crit.list());

        return rs;
    }

    @SuppressWarnings("unchecked")
    public DataResult<T> findDataResultByCriteria(int pageNumber, FetchEntitities fetch, Criterion... criterion) {
        DataResult<T> rs = new DataResult<T>();
        rs.setCount(((Long) getCurrentSession().createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult()).intValue());
        Criteria crit = getCurrentSession().createCriteria(clazz);
        Map<String, FetchMode> fetchM = fetch.getFetchModeMap();
        if (fetchM != null) {
            Set<String> keys = fetchM.keySet();
            for (String k : keys) {
                crit.setFetchMode(k, fetchM.get(k));
            }
        }
        for (Criterion c : criterion) {
            crit.add(c);
        }
        crit.setFirstResult((pageNumber - 1) * 20);
        crit.setMaxResults(20);
        
        rs.setCurrentPage(pageNumber);
        rs.setResults(crit.list());
        return rs;
        // return crit.list();
    }
    
    
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Map<String, String> aliases, Criterion... criterion) {
        Criteria crit = getCurrentSession().createCriteria(clazz);
        
        Set<String> keys = aliases.keySet();
        
        for(String key: keys) {
        	crit.createAlias(key, aliases.get(key));
        }
        
        for (Criterion c : criterion) {
            crit.add(c);
        }
        // genres
        return crit.list();
    }

    @SuppressWarnings("unchecked")
    public Long count() {
        return (Long) getCurrentSession().createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult();
    }

    public final Session getCurrentSession() {
        System.out.println("connection status.." + this.sessionFactory.getStatistics().getConnectCount());
        return this.sessionFactory.getCurrentSession();
    }
}
