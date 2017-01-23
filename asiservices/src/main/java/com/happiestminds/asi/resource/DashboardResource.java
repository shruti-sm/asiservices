package com.happiestminds.asi.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.service.DeclarationFormService;
import com.happiestminds.asi.util.JsonUtils;
import com.happiestminds.asi.vo.DeclarationFormDTO;

/**
 * 
 * @author shruti.mishra
 *
 */
@Path(URLPath.DASHBOARD)
public class DashboardResource {

	@Autowired
	DeclarationFormService formService;
	@Autowired
	LoggedInUser loggedInUsers;
	
	@GET
	@Path("/{dashboardUser}/{authToken}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@PathParam("dashboardUser") String dashboardUser, @PathParam("authToken") String authToken) throws JSONException {
		
		Long empId = loggedInUsers.getLogin(authToken);
		
		List<DeclarationFormDTO> forms = formService.findFormsByEmpId(empId);
		 if (forms == null || forms.isEmpty()) {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        } else {
	        	return Response.ok().entity(JsonUtils.objectToString(forms)).build();
	        }
	}
}