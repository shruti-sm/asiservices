<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ASI :: Female Employee late working declaration</title>
<meta name="description" content="ASI :: Female Employee late working declaration">
<meta name="author" content="Chandrakant Singh">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="style.css?v=1.0">
<!--[if lt IE 9]>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
<![endif]-->
<script src="js/lib/jquery.js"></script>
<script src="js/lib/jquery.cookie.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/asi/common.js"></script>
<script src="js/asi/emp/form.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<button class="btn btn-primary" id="logout-btn">Logout</button>
<form>
	<h1>
	Project ASI
	</h1>
	<p>Female Employee late working declaration</p>

	<hr>

	<div class="form-group">
	<label for="name">Name</label>
	<input class="form-control" id="name" placeholder="Full Name">
	</div>

	<div class="form-group">
		<label for="email">Office Address</label>
		<select class="form-control" id="officeName" placeholder="Office Address">
			<option id="" value="1">SMILES-1</option>
			<option id="" value="2">SMILES-2</option>
			<option id="" value="3">SMILES-3</option>
			<option id="" value="4">SMILES-4</option>
		</select>
	</div>

	<div class="form-group">
		<label for="project">Project</label>
		<select class="form-control" id="projectCode" placeholder="Project Name">
			<option value="1">Kohl's</option>
			<option value="2">CompuCom</option>
			<option value="3">Lowe's</option>
			<option value="4">Odeon</option>
		</select>
	</div>

	<div class="form-group">
	<label for="contactNumber">Contact Number</label>
	<input class="form-control" id="contactNumber" placeholder="Contact Number">
	</div>

	<!-- <div class="form-group">
	<label for="firstPersonContactNameAndNumber">First Person Contact Name and number</label>
	<input class="form-control" id="firstPersonContactNameAndNumber" placeholder="First Person Contact Name and Number">
	</div> -->

	<div class="form-group">
	<label for="timeOfLeaving">Time of leaving</label>
	<input class="form-control" id="timeOfLeaving" placeholder="Time of leaving">
	</div>

	<div class="form-group">
	<label for="timeOfArrival">Expected Time of arrival</label>
	<input class="form-control" id="timeOfArrival" placeholder="Expected Time of arrival">
	</div>

	<h3 style="text-align: center">Declaration</h3>
	<div style="margin: 20px auto; text-align: justify">
	Lorem ipsum dolor sit amet, consectetur adipiscing elit. In mollis ligula sit amet ipsum imperdiet molestie. Sed condimentum mollis tellus id placerat. Nulla finibus ligula vel lectus imperdiet laoreet. Nulla non ante ut nunc aliquam ultricies a eu leo. Morbi ac tortor nec augue varius fringilla at et arcu. Donec sit amet nisi sodales, accumsan orci sed, luctus nibh. Praesent auctor accumsan nunc, ut ullamcorper elit vulputate et. Praesent lobortis metus ac efficitur pellentesque. Sed porta tempus tellus eu venenatis. Aliquam eros magna, tempor vel diam id, finibus efficitur tortor. Ut id nulla vitae lacus mattis venenatis consectetur vitae magna. Integer malesuada felis nec nunc ultricies commodo. Nulla nisi lectus, facilisis nec placerat eget, mollis sit amet nunc. 
	</div>

	<button type="button"  class="btn btn-primary" id="submit-btn">I agree and Submit</button>&nbsp;<button type="submit" class="btn btn-danger">Cancel</button>
</form>
</body>
</html>