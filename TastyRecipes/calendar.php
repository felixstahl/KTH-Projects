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
<div class="header">
	<h1>February</h1>
</div>
<div class="grid-container">
	<div class="grid-header">Monday</div>
	<div class="grid-header">Tuesday</div>
	<div class="grid-header">Wednesday</div>
	<div class="grid-header">Thursday</div>
	<div class="grid-header">Friday</div>
	<div class="grid-header">Saturday</div>
	<div class="grid-header">Sunday</div>
	<div class="grid-item">1<a href="pancakes.php" class="pancake"></a></div>
	<div class="grid-item">2</div>
	<div class="grid-item">3</div>
	<div class="grid-item">4<a href="meatballs.php" class="meatball"></a></div>
	<div class="grid-item">5</div>
	<div class="grid-item">6</div>
	<div class="grid-item">7</div>
	<div class="grid-item">8</div>
	<div class="grid-item">9</div>
	<div class="grid-item">10</div>
	<div class="grid-item">11</div>
	<div class="grid-item">12</div>
	<div class="grid-item">13</div>
	<div class="grid-item">14</div>
	<div class="grid-item">15</div>
	<div class="grid-item">16</div>
	<div class="grid-item">17</div>
	<div class="grid-item">18</div>
	<div class="grid-item">19</div>
	<div class="grid-item">20</div>
	<div class="grid-item">21</div>
	<div class="grid-item">22</div>
	<div class="grid-item">23</div>
	<div class="grid-item">24</div>
	<div class="grid-item">25</div>
	<div class="grid-item">26</div>
	<div class="grid-item">27</div>
	<div class="grid-item">28</div>
</div>
</body>
</html>