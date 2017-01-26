var welcomePage = common.empPath + '/form.jsp';
$(document).ready(function() {
	
	if(common.authorized(common.userTypeEmp)) {
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
		url : '/authenticate/EMP/' + $("#emailAddress").val(),
		data : password,
		auth: false,
		success : function(response){
			if(response.code == '1') {
				common.login(response.data, common.userTypeEmp, welcomePage);
			} else {
				common.logout(common.empPath + '/index.jsp');
			}
		},
		error : function(e){
			console.log(e);
		}
	};
	common.ajax(request);
}