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
<script src="../js/asi/common.js"></script>
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
			<th></th>
		</tr>
		<tbody id="form-table"></tbody>
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
			<tr>
				<td>6886</td>
				<td>Sincy Sebastian</td>
				<td>9854644583</td>
				<td>8:00 pm</td>
				<td>8:30 pm</td>
				<th><a href="#">Reopen</a></th>
			</tr>
			<tr>
				<td>6887</td>
				<td>Bidisha Gangapahya</td>
				<td>97475443678</td>
				<td>8:04 pm</td>
				<td>8:15 pm</td>
				<th><a href="#">Reopen</a></th>
			</tr>
		</tbody>
	</table>
</div>
</div>
</div>
</body>
</html>
