<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ASI :: Female Employee late working declaration</title>
<meta name="description" content="ASI :: Female Employee late working declaration">
<meta name="author" content="Chandrakant Singh">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/styles.css?v=1.0">
<!--[if lt IE 9]>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
<![endif]-->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="Chart.min.js"></script>
<script src="Chart.bundle.min.js"></script>
<script src="jquery-3.1.1.slim.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="style.css">
<script>
$( document ).ready(function() {
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
});
</script>
</head>
<body>
<form>
	<h1>
	Project ASI
	</h1>
	<p>Female Employee late working declaration</p>

	<hr>

	<h3>Reports</h3>
	<br>
	<canvas class="reportCanvas" id="myChart"></canvas>
	
</form>
</body>
</html>