$(document).ready(
		function() {

			$("#submit-btn").click(function() {
				submitForm();
			});

			function submitForm() {

				/*var passwordJson = JSON.stringify({
					passwordJson : '12345'
				});
//				e.preventDefault();
				var request = {
					type : 'POST',
					url : '/authenticate/shruti.mishra@happiestminds.com',
					data : passwordJson,
					success : function(e){
						console.log(e);
					},
					error : function(e){
						console.log(e);
					}
				};
				common.ajax(request);*/
				
				var password = JSON.stringify({
					password : 'hmcorp'
				});
				
				var authData = JSON.stringify({
					dashboardUser : 'hmcorp',
					authToken: '64f16c4b-9fcd-45c9-8493-dc9909d2b042'
				});
				
				var request = {
					type : 'POST',
					url : '/authenticate/CORP/hmcorp',
					data : password,
					success : function(response){
						alert(response);
					},
					error : function(e){
						console.log(e);
					}

				};
				
				common.ajax(request);
				
				/*var request = {
						type : 'POST',
						url : '/requests',
						data : authData,
						success : function(response){
							console.log(response);
							alert(response);
						},
						error : function(e){
							console.log(e);
						}
				
				
				};
				
				common.ajax(request);*/
			}
			
			function formData() {
				return JSON.stringify({
					id : '1',
					officeId : $('#officeName').val(),
					projectId : $('#projectCode').val(),
					contactNumber : $('#contactNumber').val(),
					leavingDateTime : $('#timeOfLeaving').val(),
					expectedArrivalDateTime : $('#timeOfArrival').val()
				});
			}
		});