<!DOCTYPE html>
<?php 
	session_start();
	require_once 'entry.php';  
?>
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
<link rel="stylesheet" type="text/css" href="/static/comment.css">
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
	<h1>Pancake Recipe</h1>
</div>
<div class="prep">
	<div class="ingredients">
		<h1 class="prep">Ingredients</h1>
		<ul class="prep">
			<li>1 cup, all-purpose flour</li>
			<li>2 tablespoons white sugar</li>
			<li>2 teaspoons baking powder</li>
			<li>2 pounds ground beef</li>
			<li>1 teaspoon salt</li>
			<li>1 egg, beaten</li>
			<li>1 cup milk</li>
			<li>2 tablespoons vegetable</li>
		</ul>
	</div>
	<div class="dish_img">
		<img alt="Pancakes" id="pancake" src="pictures/pancakes.png"/>
	</div>
	<h1 class="prep">Directions</h1>
	<ul class="prep">
		<li>In a large bowl, mix flour, sugar, baking powder and salt. 
		Make a well in the center, and pour in milk, egg and oil. Mix until smooth.</li>
		<li>Heat a lightly oiled griddle or frying pan over medium high heat. 
		Pour or scoop the batter onto the griddle, 
		using approximately 1/4 cup for each pancake. Brown on both sides and serve hot.</li>
	</ul>
</div>
<div class="header">
	<h1>Comments</h1>
</div>
<div class="commentSection">
	<?php if(isset($_SESSION['logged-in']) && $_SESSION['logged-in']){ ?>
			<form action="storeComments.php" method="post">
				<textarea class="comment" name="message" placeholder="Leave a comment..." cols="50" rows="5"></textarea>
				<input type="hidden" name="dish" value="pancakes">
				<input type="submit" value="Publish">
			</form>
	<?php }
	$filename = __DIR__ . '/pancakescomments.txt';

	$entries = explode(";\n", file_get_contents($filename));
	for($i = count($entries) - 1; $i >= 0; $i--) {
		$entry = unserialize($entries[$i]);
		if($entry instanceof entry && !$entry->isDeleted()) {
			echo ("<p>" . $entry->getUserName() . ": ");
			echo(nl2br($entry->getMessage()));
			echo ("</p>");
			if(isset($_SESSION['username']) && $entry->getUserName() === $_SESSION['username']){
				echo("<form action='deleteComments.php'>");
				echo("<input type='hidden' name='timestamp' value='" . $entry->getTimestamp() . "'/>");
				echo("<input type='hidden' name='delete' value='pancakes'/>");// this is added
				echo("<input type='submit' value='Delete'/>");
				echo("</form>");
			}
		}
	}
	?>
</div>
</body>
</html>