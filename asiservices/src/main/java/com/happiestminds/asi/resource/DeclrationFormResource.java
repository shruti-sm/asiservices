package com.happiestminds.asi.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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

import com.happiestminds.asi.beans.LoggedInUser;
import com.happiestminds.asi.beans.Principal;
import com.happiestminds.asi.constant.MessageCode;
import com.happiestminds.asi.constant.URLPath;
import com.happiestminds.asi.constant.UserType;
import com.happiestminds.asi.service.DeclarationFormService;
import com.happiestminds.asi.service.EmployeeService;
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
public class DeclrationFormResource extends BaseResource implements MessageCode {
	
	private static final String USER_TYPE = UserType.EMP;
	
	@Autowired
	private DeclarationFormService formService;
	@Autowired
	private EmployeeService empService;
	@Autowired
	LoggedInUser loggedInUsers;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/")
    public Response submitForm(@HeaderParam("authToken") String authToken, DeclarationFormDTO form) throws ParseException {
		
		System.out.println("header in submit="+authToken);
		
		Principal principal = loggedInUsers.getLogin(authToken, USER_TYPE);
		if(principal != null) {
			List<DeclarationFormDTO> filledForm = formService.findFormByEmpIdInDuration(principal.getId(), CommonUtil.makeDate(null, 0, 0, 0, 0), 
					CommonUtil.makeDate(null, 23, 59, 59, 999));
			if(filledForm == null || filledForm.isEmpty()) {
				form.setEmpId(principal.getId());
				Long formId = formService.save(form);
				if(formId != null) {
					return response(SUCCESS, DF_SUBMIT_SUCCESS, null);
				} else {
					return response(SUCCESS, DF_SUBMIT_ERROR, null);
				}
			} else {
				return response(ERROR, DF_SUBMIT_DUPLICATE, null);
			}
		} else {
			return response(ERROR, UNAUTHORIZED, null);
		}
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getEmployeeDetails(@HeaderParam("authToken") String authToken, DeclarationFormDTO form) throws ParseException {
		
		System.out.println("header in update="+authToken);
		
		Principal principal = loggedInUsers.getLogin(authToken);
		if (principal != null) {
			if(formService.updateDeclarationForm(form) != null) {
				return response(SUCCESS, DF_UPDATE_SUCCESS, null);
			} else {
				return response(ERROR, DF_UPDATE_ERROR, null);
			}
		} else {
			return response(ERROR, UNAUTHORIZED, null);
		}
	}
	
	@GET
	@Path(URLPath.EMP)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@HeaderParam("authToken") String authToken)throws JSONException, ParseException {
		System.out.println("header in today="+authToken);
		
		Principal principal = loggedInUsers.getLogin(authToken);
		if (principal != null) {
			return response(SUCCESS, null, JsonUtils.objectToString(empService.findById(principal.getId())));
		} else {
			return response(ERROR, UNAUTHORIZED, null);
		}
	}
	
	@GET
	@Path("/{startDate}/{endDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dashboardService(@HeaderParam("authToken") String authToken
			, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws JSONException, ParseException {
		
		System.out.println("header in datewise="+authToken);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return buildEmpResponse(authToken, format.parse(startDate), format.parse(endDate));
		
	}
	
	private Response buildEmpResponse(String authToken, Date startDate, Date endDate) {
		Principal principal = loggedInUsers.getLogin(authToken);
		if (principal != null) {
			List<DeclarationFormDTO> todayForms = formService.findFormByEmpIdInDuration(principal.getId(), startDate, endDate);
			return response(SUCCESS, null, JsonUtils.objectToString(todayForms));
		} else {
			return response(ERROR, UNAUTHORIZED, null);
		}
	}
}
