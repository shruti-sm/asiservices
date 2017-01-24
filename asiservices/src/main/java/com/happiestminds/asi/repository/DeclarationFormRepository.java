package com.happiestminds.asi.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.happiestminds.asi.beans.GraphEntry;
import com.happiestminds.asi.dao.CommonDAOImpl;
import com.happiestminds.asi.domain.DeclarationForm;

/**
 * 
 * @author shruti.mishra
 *
 */
@Repository
public class DeclarationFormRepository extends CommonDAOImpl<DeclarationForm> {

	public DeclarationFormRepository() {
        setClazz(DeclarationForm.class);
    }
    
    @SuppressWarnings({ "unchecked" })
	public List<GraphEntry> findGroupByColums(List<Criterion> criterions, List<Projection> projections) {
		
    	Criteria criteria = getCurrentSession().createCriteria(DeclarationForm.class); 
    	
    	for(Criterion criterion: criterions) {
    		criteria.add(criterion);
    	}
    	
    	ProjectionList projectionList = Projections.projectionList();
    	for(Projection projection: projections) {
    		projectionList.add(projection);
    	}
    	criteria.setProjection(projectionList);
    	
    	criteria.setResultTransformer(Transformers.aliasToBean(GraphEntry.class));
    		
        return (List<GraphEntry>) criteria.list();
	}
}
