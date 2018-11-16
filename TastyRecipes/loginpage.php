<!DOCTYPE html>
<?php session_start(); ?>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Tasty Recipes</title>
<!-- custom font -->
<link href="https://fonts.googleapis.com/css?family=Mali" rel="stylesheet">
<!-- reset css stylesheet -->
<link rel="stylesheet" type="text/css" href="/static/reset.css">
<!-- create css stylesheet  -->
<link rel="stylesheet" type="text/css" href="/static/stylesheet.css">
<link rel="stylesheet" type="text/css" href="/static/loginscreen.css">
</head>

<body>
<div class="nav">
	<ul>
		<li class="nav"><a href="/">Home</a></li>
		<li class="nav"><a href="calendar.php">Calendar</a></li>
		<li class="nav"><a href="meatballs.php">Meatball Recipe</a></li>
		<li class="nav"><a href="pancakes.php">Pancake Recipe</a></li>
		<li class="nav"><a href="loginpage.php">Login</a></li>
	</ul>
</div>
<div class="headerLoginPage">
	<h1>Login</h1>
</div>
<div class="loginSection">
	<form action="loginsession.php" method="post">
		Username:
		<input type="text" name="username" placeholder="Enter Username">
		Password:
		<input type="password" name="password" placeholder="Enter Password">
		<input type="submit" value="Submit">
	</form>
</div>
</body>