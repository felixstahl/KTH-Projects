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
</head>

<body>
<div class="nav">
	<ul>
		<li class="nav"><a href="/">Home</a></li>
		<li class="nav"><a href="calendar.php">Calendar</a></li>
		<li class="nav"><a href="meatballs.php">Meatball Recipe</a></li>
		<li class="nav"><a href="pancakes.php">Pancake Recipe</a></li>
		<?php if(!isset($_SESSION['logged-in']) || !$_SESSION['logged-in']){ ?>
		<li class="nav"><a href="loginpage.php">Login</a></li>
		<?php } ?>
		<?php if(isset($_SESSION['logged-in']) && $_SESSION['logged-in']){ ?>
		<li class="nav"><a href="logoutsession.php">Logout</a></li>
		<?php } ?>
	</ul>
</div>
<div class="welcome">
	<h1 class="welcome">Welcome to Tasty Recipes!</h1>
	<p class="welcome">We are happy you made it here safe! This is just a lot of words because I want the site to look like
	I have a lot to talk about here and for it to look professional. The team behind Tasty Recipes will keep on writing tons of words
	so this section looks bigger than it actually is.
	Also, if you are interested in seeing what meal is served when, check out our <a href="calendar.php">calendar</a>.</p>
</div>
</body>
</html>
