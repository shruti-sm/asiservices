package com.happiestminds.asi.util;

import java.io.IOException;
import java.util.Properties;

import com.happiestminds.asi.exception.AsiException;

/**
 * This util will read the file available in class path and will return the
 * properties object. Also, it will get a property from a default class.
 * 
 * @author pradeep.kaushal
 * 
 */
public enum RestAppProperties {
	INSTANCE;
	
    /**
     * This is specific to rest services module. Which is available in class
     * path.
     */
    private final static String REST_APP_FILE_NAME = "rest-application.properties";
    
    
    private Properties props;
    
    /**
     * Singleton constructor to load the default properties
     * 
     */
    private RestAppProperties() {
    	String fileName = System.getProperty("ej.restservices.config");
    	
    	if(fileName == null)
    		fileName = REST_APP_FILE_NAME;
    	
    	props = loadProperties(fileName);
    }
    
    /**
     * Get a property from the default properties file.
     * 
     * @param key
     * @return value that maps to the key
     */
    public String get(String key) {
    	return props.getProperty(key);
    }

    /**
     * Load the given properties file.
     * 
     * @param fileName
     *            the file name.
     * @return properties for a given file.
     */
    public static Properties loadProperties(String fileName) {
        Properties prop = new Properties();
        try {
            prop.load(RestAppProperties.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new AsiException("Properties File is not loaded : "+fileName, e);
        }
        return prop;
    }

}
