package com.happiestminds.asi.dao;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.FetchMode;


/**
 * 
 * @author shruti.mishra
 *
 */
public class FetchEntitities {
  
	private Map<String, FetchMode> fetchM = new HashMap<String, FetchMode>();
	public FetchEntitities(String property, FetchMode fetchMode) {
		super();
		this.fetchM.put(property, fetchMode);	
	}
	
	public void addFetchModeForEntity(String property, FetchMode fetchMode){
		this.fetchM.put(property, fetchMode);
	}
	 
	public Map<String, FetchMode> getFetchModeMap(){
		return fetchM;
	}
}
