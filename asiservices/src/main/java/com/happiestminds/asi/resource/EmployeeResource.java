package com.happiestminds.asi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
import com.happiestminds.asi.service.EmployeeService;
import com.happiestminds.asi.util.JsonUtils;
import com.happiestminds.asi.vo.EmployeeDTO;

/**
 * 
 * @author shruti.mishra
 *
 */

@Component
@Path(URLPath.EMP)
public class EmployeeResource {

	private static final String USER_TYPE = UserType.EMP;
	
	@Autowired
	private EmployeeService empService;
	@Autowired
	LoggedInUser loggedInUsers;
	
	@GET
    @Path("/{authToken}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeDetails(@PathParam("authToken") String authToken) {
		
		
		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if(principal != null) {
			EmployeeDTO emp = empService.findById(principal.getId());
			if(emp != null) {
				return Response.ok().entity(JsonUtils.objectToString(emp)).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
}
