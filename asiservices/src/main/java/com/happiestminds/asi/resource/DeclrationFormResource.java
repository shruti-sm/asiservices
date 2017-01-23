package com.happiestminds.asi.resource;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import com.happiestminds.asi.service.DeclarationFormService;
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
			form.setEmpId(principal.getId());
			Long empId = formService.save(form);
			if(empId != null) {
				return Response.ok().entity("success").build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
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
				return Response.ok().entity("success").build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
}
