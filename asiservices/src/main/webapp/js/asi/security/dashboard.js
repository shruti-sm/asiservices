var loginPage = common.securityPath + '/index.jsp';
var dashboardUrl = common.securityPath;
$(document).ready(function() {
	
	
	common.authorize(common.userTypeSecurity, loginPage);
	
	$("#logout-btn").click(function() {	
		common.logout(loginPage);
	});
	
	displayDashboard();
			
});

function displayDashboard() {
	
	var d = new Date();
	//d.setDate(d.getDate() - 365);
	var startDate = common.formatDate(d) + " 00:00:00";
	var endDate = common.formatDate(d) + " 23:59:59";
	//load emp forms
	
	var request = {
			type : 'GET',
			url : dashboardUrl + "/" + startDate + "/" + endDate,
			success : function(response){
				if(response.code == 1) {
					loadForms(response.data);
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
function loadForms(data) {
	var json = $.parseJSON(data);
	$.each(json, function(key, item) {
		var tr = $('<tr></tr>').addClass('danger');
		var td1 = $('<td></td>').text(item.empCode);
		var td2 = $('<td></td>').text((item.firstName + ' ' + item.lastName));
		var td3 = $('<td></td>').text(item.contactNumber);
		var td4 = $('<td></td>').text(item.leavingDateTime);
		var td5 = $('<td></td>').text(item.expectedArrivalDateTime);
		
	  $(tr).append(td1).append(td2).append(td3).append(td4).append(td5)
	  .append('<th><a href="#">Update</a></th>').append('<th><a href="#" style="color: #ff0000">Close</a></th>');
	  $("#form-table").append(tr);
	});
}