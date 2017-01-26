package com.happiestminds.asi.resource;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.happiestminds.asi.beans.AsiResponse;
import com.happiestminds.asi.util.JsonUtils;

/**
 * 
 * @author shruti.mishra
 *
 */
@Component
public class BaseResource {	
	
	public Response response(String code, String msg, String data) {
		return Response.ok().entity(JsonUtils.objectToString(new AsiResponse(code, msg, data))).build();
	}
}
