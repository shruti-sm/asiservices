var welcomePage = common.reportsPath + '/reports.jsp';
$(document).ready(function() {
	
	if(common.authorized(common.userTypeReports)) {
		window.location.href = common.getContextUrl() + welcomePage;
	}
	
	$("#loginButton").click(function() {
		submitForm();
	});
			
});
function submitForm() {

	var password = JSON.stringify({
		password : $("#password").val()
	});
	
	var request = {
		type : 'POST',
		url : '/authenticate/CORP/' + $("#emailAddress").val(),
		data : password,
		auth: false,
		success : function(response){
			if(response.code == '1') {
				common.login(response.data, common.userTypeReports, welcomePage);
			} else {
				common.logout(common.reportsPath + '/index.jsp');
			}
		},
		error : function(e){
			console.log(e);
		}
	};
	common.ajax(request);
}