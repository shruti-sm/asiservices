<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page language="java" import="java.sql.*" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ASI :: Female Employee late working declaration</title>
<meta name="description" content="ASI :: Female Employee late working declaration">
<meta name="author" content="Chandrakant Singh">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../css/style.css?v=1.0">
<!--[if lt IE 9]>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
<![endif]-->
<script src="../js/lib/jquery.js"></script>
<script src="../js/lib/jquery.cookie.js"></script>
<!-- <script src="../js/asi/common.js"></script> -->
<script src="../js/asi/security/dashboard.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
<div class="container">
<h1>
Project ASI
</h1>
<p>Female Employee late working declaration</p>
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/asi", "root", "password@123");
	PreparedStatement stat = con.prepareStatement("select declaration_form.id, employee.emp_code as id, employee.first_name as firstName, employee.last_name as lastName, employee.contact_number as contactNumber, DATE_FORMAT(declaration_form.leaving_date_time,'%H:%i:%s') as leavingTime, DATE_FORMAT(declaration_form.expected_arrival_date_time,'%H:%i:%s') as arrivalTime, status as status, TIMESTAMPDIFF(MINUTE, NOW(), declaration_form.expected_arrival_date_time) as timeToCall from declaration_form join employee on declaration_form.emp_id = employee.id where DATE(declaration_form.leaving_date_time) = CURDATE()");
	ResultSet res = stat.executeQuery();
	List<Map<String, String>> records = new ArrayList<Map<String, String>>();
	ResultSetMetaData data = res.getMetaData();
	int count = data.getColumnCount();
	while(res.next()) {
		Map<String, String> record = new HashMap<String, String>();
		for (int i=0; i<count; i++) {
			record.put(data.getColumnName(i + 1), res.getString(i + 1));
		}
		records.add(record);
	}
	res.close();
	stat.close();
	con.close();
%>
<div id="current-requests">
<table>
<tr><td><button class="btn btn-primary" id="logout-btn">Logout</button></td></tr>
<tr>
<td width="100%"><h3>Current</h3></td>
<td nowrap="nowrap"><button class="btn btn-primary" onclick="$('#current-requests').show();$('#closed-requests').hide();">Current</button>&nbsp;<button class="btn btn-warning" onclick="$('#current-requests').hide();$('#closed-requests').show();">Closed</button></td>
</tr>
</table>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Contact Number</th>
			<th>Leaving Time</th>
			<th>Arrival Time</th>
			<th></th>
		</tr>
		<tbody id="form-table">
			<%
			for(Map<String, String> record: records) {
				if (!record.get("status").equals("1")) {
					continue;
				}
			%>
			<tr>
				<td><%=record.get("emp_code") %></td>
				<td><%=record.get("first_name") %> <%=record.get("last_name") %></td>
				<td><%=record.get("contact_number") %></td>
				<td><%=record.get("leavingTime") %></td>
				<td><%=record.get("arrivalTime") %></td>
				<td><a href="statusChange.jsp?id=<%=record.get("id") %>&status=2" style="color: red">Close</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</div>
</div>
<div id="closed-requests" style="display: none">
<table>
<tr>
<td width="100%"><h3>Closed</h3></td>
<td nowrap="nowrap"><button class="btn btn-primary" onclick="$('#current-requests').show();$('#closed-requests').hide();">Current</button>&nbsp;<button class="btn btn-warning" onclick="$('#current-requests').hide();$('#closed-requests').show();">Closed</button></td>
</tr>
</table>
<div class="table-responsive">
	<table class="table table-hover">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Contact Number</th>
			<th>Leaving Time</th>
			<th>Arrival Time</th>
			<th></th>
		</tr>
		<tbody>
			<%
			for(Map<String, String> record: records) {
				if (!record.get("status").equals("2")) {
					continue;
				}
			%>
			<tr>
				<td><%=record.get("emp_code") %></td>
				<td><%=record.get("first_name") %> <%=record.get("last_name") %></td>
				<td><%=record.get("contact_number") %></td>
				<td><%=record.get("leavingTime") %></td>
				<td><%=record.get("arrivalTime") %></td>
				<td><a href="statusChange.jsp?id=<%=record.get("id") %>&status=1" style="color: blue">Reopen</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</div>
</div>
</div>
</body>
</html>
