var loginPage = common.reportsPath + '/index.jsp';
var dashboardUrl = common.reportsPath;
$(document).ready(function() {
	
	common.authorize(common.userTypeReports, loginPage);
	
	$("#logout-btn").click(function() {	
		common.logout(loginPage);
	});
	
	buildCharts();
			
});

function buildCharts() {
	var ctx = $("#myChart");
	var data = {
	    labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
	    datasets: [
		{
		    label: "Employee count",
		    backgroundColor: [
		        'rgba(255, 99, 132, 0.2)',
		        'rgba(54, 162, 235, 0.2)',
		        'rgba(255, 206, 86, 0.2)',
		        'rgba(75, 192, 192, 0.2)',
		        'rgba(153, 102, 255, 0.2)',
		        'rgba(255, 159, 64, 0.2)'
		    ],
		    borderColor: [
		        'rgba(255,99,132,1)',
		        'rgba(54, 162, 235, 1)',
		        'rgba(255, 206, 86, 1)',
		        'rgba(75, 192, 192, 1)',
		        'rgba(153, 102, 255, 1)',
		        'rgba(255, 159, 64, 1)'
		    ],
		    borderWidth: 1,
		    data: [65, 59, 80, 81, 56, 55, 40],
		}
	    ]
	};

	var myBarChart = new Chart(ctx, {
		type: 'bar',
		data: data,
		options: {
			scales: {
			    yAxes: [{
				ticks: {
				    beginAtZero:true
				}
			    }]
			}
		    }
	});	
}