package com.happiestminds.asi.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happiestminds.asi.constant.URLPath;
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

	@Autowired
	private EmployeeService empService;
	
	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeDetails(@PathParam("id") Long id) {
		
		EmployeeDTO emp = empService.findById(id);
		if(emp != null) {
			return Response.ok().entity(JsonUtils.objectToString(emp)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
