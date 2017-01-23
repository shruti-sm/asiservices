package com.happiestminds.asi.resource;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.service.EmployeeService;
import com.happiestminds.asi.util.JsonUtils;

/**
 * 
 * @author shruti.mishra
 * 
 */

@Component
@Path("")
public class LoginResource {
	
	@Autowired
	LoggedInUser loggedInUsers;
	
	@Autowired
	private EmployeeService empService;

	@POST
	@Path(URLPath.LOGIN + "/{userName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginViaUserName(@PathParam("userName") String userName,
			String passwordJson) throws JSONException {

		JSONObject json = new JSONObject(passwordJson);
		String password = json.getString("password");
		Long empId = empService.checkLogin(userName, password);
		if (empId != null) {
			String token = UUID.randomUUID().toString();
			loggedInUsers.addLogin(token, empId);
	
			displayLoggedInUsers();
			
			return Response.ok().entity(JsonUtils.objectToString(token))
					.build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
    @Path(URLPath.LOGOUT + "/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("token") String token) {
		
		if(loggedInUsers.removeLogin(token)) {
			displayLoggedInUsers();
			return Response.ok().entity("true").build();
		} else {
			return Response.ok().entity("false").build();
		}
	}
	
	private void displayLoggedInUsers() {
		Map<String, Long> login = loggedInUsers.getLoggedInUsers();
		Set<String> keys = login.keySet();
		for(String key: keys) {
			System.out.println("token="+key + "empId="+login.get(key));
		}
	}
}
