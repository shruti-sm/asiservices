package com.happiestminds.asi.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.beans.AsiMessage;
import com.happiestminds.asi.beans.GraphEntry;
import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.beans.ReportEntry;
import com.happiestminds.asi.constant.GraphId;
import com.happiestminds.asi.constant.ReportId;
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
@Component
@Path(URLPath.REPORT)
public class ReportResource {

	@Autowired
	private LoggedInUser loggedInUsers;
	@Autowired
	private DeclarationFormService formService;
	
	private static final String USER_TYPE = UserType.CORP;
	
	@GET
	@Path("/{authToken}/{reportId}/{n}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateGraphs(@PathParam("authToken") String authToken,
			@PathParam("reportId") String reportId,
			@PathParam("n") int n) {

		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if (principal != null) {
			if (ReportId.TOP_LATE_LEAVERS.equals(reportId)) {
				return generateResponse(formService.findTopLateLeavers(null, null, n));
			} /*else if (ReportId.TOP_LATE_LEAVER.equals(reportId)) {
				return topLateLeaver();
			}*//*  else if(GraphId.CURRENT_YEAR_HOUR.equals(grapId)) {
				return lastOneYearHourWise();
			}*/ else {
				return Response.ok().entity(JsonUtils.objectToString(new AsiMessage("RR1", "Incorrect Report ID"))).build();
			}

		} else {
			return Response.ok().entity(new AsiMessage("LOG1", "Not authorized to access the service")).build();
		}
	}
	
	@GET
	@Path(URLPath.REPORT_EMP + "/{authToken}/{userName}/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@PathParam("authToken") String authToken,
			@PathParam("userName") String userName,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate)
			throws JSONException, ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("startDate="+startDate+", p="+format.parse(startDate));
		System.out.println("endDate="+endDate+format.parse(endDate));
		
		Principal principal = loggedInUsers.getLogin(authToken);
		if (principal != null) {
			List<DeclarationFormDTO> todayForms = formService.findFormByEmpIdCodeUserNameInDuration(userName, 
					format.parse(startDate), format.parse(endDate));
			
			System.out.println("todaysForms="+todayForms);
			
			return Response.ok().entity(JsonUtils.objectToString(todayForms)).build();
		} else {
			return Response.ok().entity(new AsiMessage("LOG1", "Not authorized to access the service")).build();
		}
	}
	
private Response generateResponse(List<ReportEntry> entries) {
		
		return Response.ok().entity(JsonUtils.objectToString(entries)).build();
	}
}
