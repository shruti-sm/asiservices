package com.happiestminds.asi.repository;

import org.springframework.stereotype.Repository;

import com.happiestminds.asi.dao.CommonDAOImpl;
import com.happiestminds.asi.domain.Office;

/**
 * 
 * @author shruti.mishra
 *
 */
@Repository
public class OfficeRepository extends CommonDAOImpl<Office> {

    public OfficeRepository() {
        setClazz(Office.class);
    }
}
