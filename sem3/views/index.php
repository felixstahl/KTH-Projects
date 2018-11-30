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
</head>

<body>
<div class="nav">
	<ul>
		<li class="nav"><a href="Firstpage">Home</a></li>
		<li class="nav"><a href="Calendarpage">Calendar</a></li>
		<li class="nav"><a href="Meatballpage">Meatball Recipe</a></li>
		<li class="nav"><a href="Pancakepage">Pancake Recipe</a></li>
		<?php if(($this->session->get('username')) == null){ ?>
		<li class="nav"><a href="Loginpage">Login</a></li>
		<?php } ?>
		<?php if(($this->session->get('username')) != null){ ?>
		<li class="nav"><a href="Logoutuser">Logout</a></li>
		<?php } ?>
	</ul>
</div>
<div class="welcome">
	<h1 class="welcome">Welcome to Tasty Recipes!</h1>
	<p class="welcome">We are happy you made it here safe! This is just a lot of words because I want the site to look like
	I have a lot to talk about here and for it to look professional. The team behind Tasty Recipes will keep on writing tons of words
	so this section looks bigger than it actually is.
	Also, if you are interested in seeing what meal is served when, check out our <a href="Calendarpage">calendar</a>.</p>
</div>
</body>
</html>
