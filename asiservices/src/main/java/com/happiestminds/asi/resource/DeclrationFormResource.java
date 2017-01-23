package com.happiestminds.asi.resource;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.domain.DeclarationForm;
import com.happiestminds.asi.service.DeclarationFormService;
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
	
	
	@Autowired
	private DeclarationFormService formService;
	
	/*@GET
    @Path(URLPath.EMP_FORM_GET_ALL_FORMS + "/{empCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllForms(@PathParam("empCode") String empCode) {
		
		List<DeclarationForm> forms = formService.findFormsByEmpCode(empCode);
		 if (forms == null || forms.isEmpty()) {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        } else {
	        	return Response.ok().entity(JsonUtils.objectToString(forms)).build();
	        }
	}	*/
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(URLPath.EMP_FORM_SUBMIT)
    public Response submitForm(DeclarationFormDTO form) throws ParseException {
		
		System.out.println("form="+form);
		
		Long empId = formService.save(form);
		if(empId != null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok().entity("Form successfully submitted").build();
		}	
	}
}
