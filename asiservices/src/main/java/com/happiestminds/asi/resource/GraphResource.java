package com.happiestminds.asi.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.beans.AsiResponse;
import com.happiestminds.asi.beans.GraphEntry;
import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.GraphId;
import com.happiestminds.asi.constant.MessageCode;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
import com.happiestminds.asi.service.DeclarationFormService;
import com.happiestminds.asi.util.CommonUtil;
import com.happiestminds.asi.util.JsonUtils;

/**
 * 
 * @author shruti.mishra
 * 
 */
@Component
@Path(URLPath.GRAPH)
public class GraphResource extends BaseResource implements MessageCode {

	private static final String USER_TYPE = UserType.CORP;

	@Autowired
	private LoggedInUser loggedInUsers;
	@Autowired
	private DeclarationFormService formService;

	@GET
	@Path("/{grapId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateGraphs(@HeaderParam("authToken") String authToken,
			@PathParam("grapId") String grapId) {

		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if (principal != null) {
			if (GraphId.SEVEN_DAYS.equals(grapId)) {
				return lastSevenDays();
			} else if (GraphId.PROJECT.equals(grapId)) {
				return lastMonthForProject();
			} else if(GraphId.CURRENT_YEAR.equals(grapId)) {
				return lastOneYear();
			}/*  else if(GraphId.CURRENT_YEAR_HOUR.equals(grapId)) {
				return lastOneYearHourWise();
			}*/ else {
				return response(ERROR, GR_INCORRECT_ID, null);
			}

		} else {
			return response(ERROR, UNAUTHORIZED, null);
		}
	}

	private Response lastSevenDays() {

		List<GraphEntry> entries = formService.findFormCountsByDuration(
				CommonUtil.makeDate(-7, 0, 0, 0, 0), CommonUtil.makeDate(null, 23, 59, 59, 999), "leavingDateTime");
		return response(SUCCESS, null, JsonUtils.objectToString(entries));
	}

	private Response lastMonthForProject() {

		List<GraphEntry> entries = formService.findFormCountsByDuration(
				CommonUtil.makeDate(-30, 0, 0, 0, 0), CommonUtil.makeDate(null, 23, 59, 59, 999), "project.id");
		return response(SUCCESS, null, JsonUtils.objectToString(entries));
	}

	private Response lastOneYear() {
		List<GraphEntry> entries = formService.findFormCountsByDuration(
				CommonUtil.makeDate(-365, 0, 0, 0, 0), CommonUtil.makeDate(null, 23, 59, 59, 999), "leavingDateTime");
		return response(SUCCESS, null, JsonUtils.objectToString(entries));
	}
	
	/*private Response lastOneYearHourWise() {
		List<GraphEntry> entries = formService.findFormCountsByDuration(
				CommonUtil.makeDate(-365, 0, 0, 0, 0), CommonUtil.makeDate(null, 23, 59, 59, 999), "leavingDateTime");
		
		Map<>
		return generateResponse(entries);
	}*/

	
	
	/*private Map<String, Long> createHourBucket(List<GraphEntry> entries) {
		
		Map<String, Long> map = new HashMap<String, Long>();
		for
	}*/
}
