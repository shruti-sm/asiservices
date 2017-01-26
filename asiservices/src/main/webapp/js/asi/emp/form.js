var loginPage = common.empPath + '/index.jsp';
var dashboardUrl = common.empPath + "/emp";
$(document).ready(function() {
	
	
	common.authorize(common.userTypeSecurity, loginPage);
	
	$("#logout-btn").click(function() {	
		common.logout(loginPage);
	});
	
	$("#submit-btn").click(function() {
		submitForm();
	});
	fetchPreviousForm();	
});

function fetchPreviousForm() {
	var request = {
			type : 'GET',
			url : dashboardUrl,
			success : function(response) {
				populateForm($.parseJSON(response.data));
			},
			error : function(e){
				console.log(e);
			}
		};
		common.ajax(request);
}

function populateForm(emp) {
	var now = common.formatDateStandard(new Date(Date.now()));
	var expected = common.formatDateStandard(new Date(Date.now() + emp.lastExpectedArrivalTime));

	
	$("#name").val(emp.firstName + " " +emp.lastName);
	$('#officeName').val(emp.officeId);
	$('#projectCode').val(emp.projectId);
	$('#contactNumber').val(emp.contactNumber);
	$('#timeOfLeaving').val(now);
	$('#timeOfArrival').val(expected);
}

function submitForm() {
	
	var data = JSON.stringify({
		officeId : $('#officeName').val(),
		projectId : $('#projectCode').val(),
		contactNumber : $('#contactNumber').val(),
		leavingDateTime : $('#timeOfLeaving').val(),
		expectedArrivalDateTime : $('#timeOfArrival').val()
	});
	var request = {
		type : 'POST',
		url : dashboardUrl + '/form',
		data : data,
		success : function(response) {
			if(response.code == '1') {
				common.redirect(common.empPath + '/requests.jsp');
			} else{
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