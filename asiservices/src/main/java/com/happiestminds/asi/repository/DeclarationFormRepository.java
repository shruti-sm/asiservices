package com.happiestminds.asi.repository;

import org.springframework.stereotype.Repository;

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
}
