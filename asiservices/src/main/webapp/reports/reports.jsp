<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page language="java" import="java.sql.*"%>
<%!
public List<Map<String, String>> getGraphData(String sql) {
	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/asi", "root", "password@123");
		Statement stat = con.createStatement();
		ResultSet res = stat.executeQuery(sql);
		while(res.next()) {
			Map<String, String> record = new LinkedHashMap<String, String>();
			record.put("variableName", res.getString(1));
			record.put("value", res.getString(2));
			result.add(record);
		}
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
	return result;
}
public String getPart(List<Map<String, String>> data, boolean keys, boolean split) {
	String str = "[";
	for(Map<String, String> rec: data) {
		if (keys) {
			if (!split) {
				str += "\"" + rec.get("variableName") + "\", ";
			}
			else {
				str += "\"" + rec.get("variableName").split(" ")[0] + "\", ";
			}
		}
		else {
			str += rec.get("value") + ", ";
		}
	}
	str = str.substring(0, str.lastIndexOf(","));
	str += "]";
	return str;
}
%>
<%
List<Map<String, String>> graphResult1 = getGraphData("select leaving_date_time, COUNT(emp_id) from declaration_form GROUP BY DATE(leaving_date_time) HAVING TIMESTAMPDIFF(DAY,leaving_date_time, NOW()) <= 7");
List<Map<String, String>> graphResult2 = getGraphData("select project.name, COUNT(declaration_form.emp_id) from declaration_form JOIN project ON declaration_form.project_id=project.id GROUP BY project.name;");
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ASI :: Female Employee late working declaration</title>
<meta name="description" content="ASI :: Female Employee late working declaration">
<meta name="author" content="Chandrakant Singh">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lt IE 9]>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
<![endif]-->
<script src="../js/lib/jquery.js"></script>
<script src="../js/lib/jquery.cookie.js"></script>
<!-- <script src="../js/asi/common.js"></script> -->
<!-- <script src="../js/asi/reports/reports.js"></script> -->
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../Chart.min.js"></script>
<script src="../Chart.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
<button class="btn btn-primary" id="logout-btn">Logout</button>
<form>
	<h1>
	Project ASI
	</h1>
	<p>Female Employee late working declaration</p>

	<hr>

	<h3>Reports</h3>
	<br>
	<div class="col-md-6">
		<canvas class="reportCanvas" id="myChart1"></canvas>
		<script>
		var ctx = document.getElementById("myChart1");
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: <%=getPart(graphResult1, true, true)%>,
		        datasets: [{
		            label: 'Employees leaving late in the past 7 days',
		            data: <%=getPart(graphResult1, false, false)%>,
		        }]
		    },
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
		</script>
	</div>
	<div class="col-md-6">
		<canvas class="reportCanvas" id="myChart2"></canvas>
		<script>
		var ctx = document.getElementById("myChart2");
		var myChart = new Chart(ctx, {
		    type: 'bar',
		    data: {
		        labels: <%=getPart(graphResult2, true, true)%>,
		        datasets: [{
		            label: 'Employee leaving late for projects',
		            data: <%=getPart(graphResult2, false, false)%>,
		        }]
		    },
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
		</script>
	</div>
	
</form>
</body>
</html>
