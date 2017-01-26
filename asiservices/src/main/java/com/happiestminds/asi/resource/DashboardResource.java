package com.happiestminds.asi.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.MessageCode;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
import com.happiestminds.asi.service.DeclarationFormService;
import com.happiestminds.asi.util.JsonUtils;

/**
 * 
 * @author shruti.mishra
 * 
 */
@Path(URLPath.DASHBOARD)
public class DashboardResource extends BaseResource implements MessageCode {

	private static final String USER_TYPE = UserType.SECURITY;

	@Autowired
	DeclarationFormService formService;
	@Autowired
	LoggedInUser loggedInUsers;
	
	
	@GET
	@Path("/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@HeaderParam("authToken") String authToken,
			@PathParam("startDate") String startDateString,
			@PathParam("endDate") String endDateString)
			throws JSONException, ParseException {

		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if (principal != null) {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return response(SUCCESS, null, JsonUtils.objectToString(formService.findFormsByDuration(format.parse(startDateString), 
					format.parse(endDateString))));
		} else {
			return response(ERROR, UNAUTHORIZED, null);
		}
	}
}