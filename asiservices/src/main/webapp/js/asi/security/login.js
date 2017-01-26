var welcomePage = common.securityPath + '/dashboard.jsp';
$(document).ready(function() {
	
	if(common.authorized(common.userTypeSecurity)) {
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
		url : '/authenticate/SECURITY/' + $("#emailAddress").val(),
		data : password,
		auth: false,
		success : function(response){
			if(response.code == '1') {
				common.login(response.data, common.userTypeSecurity, welcomePage);
			} else {
				common.logout(common.securityPath + '/index.jsp');
			}
		},
		error : function(e){
			console.log(e);
		}
	};
	common.ajax(request);
}