package com.happiestminds.asi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.happiestminds.asi.exception.AsiException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    /**
     * Convert an object to its Json String.
     * 
     * @param obj
     * @return
     */
    public static String objectToString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
            throw new AsiException("Exception converting object to String", e);
        }
    }

    /**
     * Convert an object to its Json String.
     * 
     * @param obj
     * @return
     */
    public static JsonNode objectToNode(Object obj) {
        return new ObjectMapper().convertValue(obj, JsonNode.class);
    }

    /**
     * Convert an object to its Json String.
     * 
     * @param obj
     * @return
     */
    public static JsonNode mapToNode(Map<String, Object> map) {
        ObjectMapper mapper= new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm a z");
        mapper.getSerializationConfig().with(dateFormat);
        mapper.getDeserializationConfig().with(dateFormat);
        mapper.configure(SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS , false);
     
        return new ObjectMapper().convertValue(objectToMap(map), JsonNode.class);
    }

    /**
     * Convert a map object to its Json String.
     * 
     * @param obj
     * @return
     */
    public static String mapToJsonString(Map<String, Object> map) {
        return mapToNode(map).toString();
    }
    

    /**
     * Convert an object to its Json String.
     * 
     * @param obj
     * @return
     */
    public static JSONObject objectToJsonObject(Object obj) {
        try {
            return new JSONObject(objectToString(obj));
        } catch (JSONException e) {
			throw new AsiException("Exception converting object to JSONObject", e);
        }
    }

    public static JsonNode stringToJsonNode(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        JsonNode actualObj;
        try {
            actualObj = mapper.readValue(jsonString, JsonNode.class);
        } catch (Exception e) {
            throw new AsiException("Exception converting string to json node", e);
        }
        return actualObj;
    }

    /**
     * Convert a Java Object to Map.
     * 
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> objectToMap(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm a z");
        mapper.getSerializationConfig().with(dateFormat);
        mapper.getDeserializationConfig().with(dateFormat);
        mapper.configure(SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS , false); 
        return mapper.convertValue(object, Map.class);
    }

    /**
     * 
     * @param url
     * @return
     */
    public static String getJsonStringFromUrl(String url) {

        try {
            URL u = new URL(url);
            URLConnection c = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String inputLine;
            StringBuffer b = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                b.append(inputLine + "\n");
            }
            in.close();
            return b.toString();
        } catch (MalformedURLException e) {
            return null;
            //throw new AsiException("JsonUtils.getJsonStringFromUrl, Malformed URL", e);
        } catch (IOException e) {
            return null;
            //throw new AsiException("JsonUtils.getJsonStringFromUrl, IOException", e);
        }

    }

    
  /*  @SuppressWarnings("unchecked")
    public static Class stringToObject(String jsonString, Class clazz) {
        try {
            return new ObjectMapper().readValue(jsonString, clazz);
        } catch (JsonParseException e) {
            throw new AsiException("JsonUtils.stringToMap, Exception in parsing json string", e);
        } catch (JsonMappingException e) {
            throw new AsiException("JsonUtils.stringToMap, Exception in mapping json string to Map object", e);
        } catch (IOException e) {
            throw new AsiException("JsonUtils.stringToMap, IOException", e);
        }
    }*/
}
