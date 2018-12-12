<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Tasty Recipes</title>
<!-- custom font -->
<link href="https://fonts.googleapis.com/css?family=Mali" rel="stylesheet">
<!-- reset css stylesheet -->
<link rel="stylesheet" type="text/css" href="../../resources/css/reset.css">
<!-- create css stylesheet  -->
<link rel="stylesheet" type="text/css" href="../../resources/css/stylesheet.css">
<link rel="stylesheet" type="text/css" href="../../resources/css/loginscreen.css">

<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
  <script src="../../resources/js/app.js"></script>
</head>
<body>
<div class="nav">
	<ul>
		<li class="nav"><a href="Firstpage">Home</a></li>
		<li class="nav"><a href="Calendarpage">Calendar</a></li>
		<li class="nav"><a href="Meatballpage">Meatball Recipe</a></li>
		<li class="nav"><a href="Pancakepage">Pancake Recipe</a></li>
		<li class="nav"><a href="Loginpage">Login</a></li>
	</ul>
</div>
<div class="headerLoginPage">
	<h1>Login</h1>
	<div id="wronglogin"></div>
</div>
	
<div class="loginSection">
	<form id="loginform">
		Username:
		<input id="username" type="text" name="username" placeholder="Enter Username">
		Password:
		<input id="password" type="password" name="password" placeholder="Enter Password">
		<button type="submit">Login</button>
	</form>
</div>
</body>