package com.happiestminds.asi.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.Status;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
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

	private static final String USER_TYPE = UserType.SECURITY;

	@Autowired
	DeclarationFormService formService;
	@Autowired
	LoggedInUser loggedInUsers;
	
	
	@GET
	@Path("/{authToken}/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@PathParam("authToken") String authToken,
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString)
			throws JSONException, ParseException {

		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if (principal != null) {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return generateResponse(formService.findFormsByDuration(format.parse(startDateString), 
					format.parse(endDateString)));
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	private Response generateResponse(List<DeclarationFormDTO> forms) {
		return Response.ok().entity(JsonUtils.objectToString(forms)).build();
	}
}