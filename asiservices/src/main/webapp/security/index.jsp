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
<script src="../jquery-3.1.1.slim.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="../style.css">
<script type="text/javascript">
$(function() {
	$('#loginButton').click(function(event){
		var pwd = $('#password').val();
		$.post( "/authenticate", { password: pwd })
		  .done(function( response ) {
		     // Get the authToken and store it in a cookie.
			document.location = 'dashboard.jsp';
		  });
	});
});
</script>
</head>
<body>
<form>
	<h1>
	Project ASI - Dashboard
	</h1>
	<hr>

	<div class="form-group">
	<label for="emailAddress">Email Address</label>
	<input class="form-control" id="emailAddress" placeholder="Email Address">
	</div>

	<div class="form-group">
	<label for="password">Password</label>
	<input type="password" class="form-control" id="password" placeholder="Password">
	</div>

	<button type="submit" class="btn btn-primary" id="loginButton">Login</button>
</form>
</body>
</html>
