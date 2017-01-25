package com.happiestminds.asi.resource;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.beans.AsiMessage;
import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
import com.happiestminds.asi.service.DeclarationFormService;
import com.happiestminds.asi.util.CommonUtil;
import com.happiestminds.asi.util.JsonUtils;
import com.happiestminds.asi.vo.DeclarationFormDTO;

/**
 * 
 * @author shruti.mishra
 *
 */
@Component
@Path(URLPath.EMP_FORM)
public class DeclrationFormResource {
	
	private static final String USER_TYPE = UserType.EMP;
	
	@Autowired
	private DeclarationFormService formService;
	@Autowired
	LoggedInUser loggedInUsers;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(URLPath.EMP_FORM_SUBMIT + "/{authToken}")
    public Response submitForm(@PathParam("authToken") String authToken, DeclarationFormDTO form) throws ParseException {
		
		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if(principal != null) {
			
			
			//check if form is already filled in the same day
			List<DeclarationFormDTO> filledForm = formService.findFormByEmpIdInDuration(principal.getId(), CommonUtil.makeDate(null, 0, 0, 0, 0), 
					CommonUtil.makeDate(null, 23, 59, 59, 999));
			
			if(filledForm == null || filledForm.isEmpty()) {
				form.setEmpId(principal.getId());
				Long formId = formService.save(form);
				if(formId != null) {
					return Response.ok().entity("success").build();
				} else {
					return Response.ok().entity("error").build();
				}
			} else {
				return Response.ok().entity("Form filled already").build();
			}
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{authToken}")
	public Response getEmployeeDetails(@PathParam("authToken") String authToken, DeclarationFormDTO form) throws ParseException {
		
		Principal principal = loggedInUsers.getLogin(authToken);
		if (principal != null) {
			if(formService.updateDeclarationForm(form) != null) {
				return Response.ok().entity(JsonUtils.objectToString(new AsiMessage("DF1", "Form submitted successfully"))).build();
			} else {
				return Response.ok().entity(JsonUtils.objectToString(new AsiMessage("DF2", "Error in submitting the form"))).build();
			}
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@GET
	@Path(URLPath.EMP_FORM_TODAY + "/{authToken}/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@PathParam("authToken") String authToken,
			@PathParam("userName") String userName)
			throws JSONException, ParseException {

		Principal principal = loggedInUsers.getLogin(authToken);
		if (principal != null) {
			List<DeclarationFormDTO> todayForms = formService.findFormByEmpIdCodeUserNameInDuration(userName, 
					CommonUtil.makeDate(null, 0, 0, 0, 0), CommonUtil.makeDate(null, 23, 59, 59, 999));
			
			return Response.ok().entity(JsonUtils.objectToString(todayForms)).build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
}
