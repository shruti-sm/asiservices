var loginPage = common.empPath + '/index.jsp';
var dashboardUrl = common.empPath + '/emp';
$(document).ready(function() {
	common.authorize(common.userTypeEmp, loginPage);
	
	$("#logout-btn").click(function() {	
		common.logout(loginPage);
	});
	
	displayRequestDetails();
			
});

function displayRequestDetails() {
	
	var d = new Date();
	var startDate = common.formatDate(d) + " 00:00:00";
	var endDate = common.formatDate(d) + " 23:59:59";
	//load emp forms
	
	var request = {
			type : 'GET',
			url : dashboardUrl + "/form/" + startDate + "/" + endDate,
			success : function(response){
				if(response.code == 1) {
					display($.parseJSON(response.data));
				} else {
					if(response.msg == 'Not authorized to access the service') {
						common.logout(loginPage);
					}
				}
			},
			error : function(e){
				console.log(e);
			}
		};
		common.ajax(request);
}

function display(forms) {
	var form = forms[0];
	$("#leavingDateTime").text(form.leavingDateTime);
	$("#expectedArrivalDateTime").text(form.expectedArrivalDateTime);
}