package com.happiestminds.asi.repository;

import org.springframework.stereotype.Repository;

import com.happiestminds.asi.dao.CommonDAOImpl;
import com.happiestminds.asi.domain.Project;

/**
 * 
 * @author shruti.mishra
 *
 */
@Repository
public class ProjectRepository extends CommonDAOImpl<Project> {

    public ProjectRepository() {
        setClazz(Project.class);
    }
}
