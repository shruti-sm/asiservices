package com.happiestminds.asi.resource;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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

import com.happiestminds.asi.beans.AsiResponse;
import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.MessageCode;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
import com.happiestminds.asi.service.EmployeeService;
import com.happiestminds.asi.util.JsonUtils;
import com.happiestminds.asi.util.RestAppProperties;

/**
 * 
 * @author shruti.mishra
 * 
 */

@Component
@Path("")
public class LoginResource extends BaseResource implements MessageCode {
	
	@Autowired
	LoggedInUser loggedInUsers;
	
	@Autowired
	private EmployeeService empService;

	@POST
	@Path(URLPath.LOGIN + "/{userType}/{userName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginViaUserName(@PathParam("userType") String userType,
			@PathParam("userName") String userName,
			String passwordJson) throws JSONException {

		Principal principal = null;
		
		String token = UUID.randomUUID().toString();
		JSONObject json = new JSONObject(passwordJson);
		String password = json.getString("password");
		
		if(UserType.EMP.equalsIgnoreCase(userType)) {
			Long empId = empService.checkLogin(userName, password);
			if(empId != null) {
				principal = loggedInUsers.addLogin(token, empId, UserType.EMP);
			}	
		} else if(UserType.SECURITY.equalsIgnoreCase(userType) && RestAppProperties.INSTANCE.get("SECURITY_USER_NAME").equalsIgnoreCase(userName)
				 && RestAppProperties.INSTANCE.get("SECURITY_PASSWORD").equalsIgnoreCase(password)) {
			
			principal = loggedInUsers.addLogin(token, Long.parseLong(RestAppProperties.INSTANCE.get("SECURITY_ID")), UserType.SECURITY);
			
		} else if(UserType.CORP.equalsIgnoreCase(userType) && RestAppProperties.INSTANCE.get("CORP_USER_NAME").equalsIgnoreCase(userName)
				 && RestAppProperties.INSTANCE.get("CORP_PASSWORD").equalsIgnoreCase(password)) {

			principal = loggedInUsers.addLogin(token, Long.parseLong(RestAppProperties.INSTANCE.get("CORP_ID")), UserType.CORP);
		}
		
		displayLoggedInUsers();
		
		if(principal != null) {
			return response(SUCCESS, null, token);
		} else {
			return response(ERROR, LOG_INCORRECT_LOGIN, null);
		}
	}
	
	@GET
    @Path(URLPath.LOGOUT)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam("authToken") String authToken) {
		
		if(loggedInUsers.removeLogin(authToken) != null) {
			displayLoggedInUsers();
			return response(SUCCESS, LOG_OUT_TRUE, null);
		} else {
			return response(ERROR, LOG_OUT_FALSE, null);
		}
	}
	
	private void displayLoggedInUsers() {
		Map<String, Principal> login = loggedInUsers.getLoggedInUsers();
		Set<String> keys = login.keySet();
		System.out.println("Currently logged in users");
		for(String key: keys) {
			System.out.println("token="+key + "empId="+login.get(key));
		}
	}
}
