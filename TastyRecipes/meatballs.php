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
	<h1>Meatball Recipe</h1>
</div>
<div class="prep">
	<div class="ingredients">
		<h1 class="prep">Ingredients</h1>
		<ul class="prep">
			<li>4 eggs</li>
			<li>1 cup milk</li>
			<li>8 slices of white bread, torn</li>
			<li>2 pounds ground beef</li>
			<li>1/4 cup finely chopped onion</li>
			<li>4 teaspoons baking powder</li>
			<li>1 teaspoon pepper</li>
			<li>2 tablespoons shortening</li>
			<li>2 cans(10-3/4 ounces each) condensed cream of chicken soup, undiluted</li>
			<li>2 cans(10-3/4 ounces each) condensed cream of mushroom soup, undiluted</li>
			<li>1 can(12 ounces) evaporated milk</li>
			<li>Minced fresh parsely</li>
		</ul>
	</div>
	<div class="dish_img">
		<img alt="Meatballs" src="pictures/meatballs.png"/>
	</div>
	<h1 class="prep">Directions</h1>
	<ul class="prep">
		<li>In a large bowl, beat eggs and milk. Add bread; mix gently and let stand for 5 minutes. 
		Add beef, onion, baking powder, salt and pepper; mix well(mixture will be soft). Shape into 1-in. balls.</li>
		<li>In large skillet, brown meatballs, a few at a time, in shortening. Place in an ungreased 3-qt. baking dish. 
		In a bowl, stir soups and milk until smooth; pour over meatballs. Bake, uncovered, at 350 degrees celsius for 1 hour. 
		Sprinkle with parsley. Yield 8-10 servings.</li>
	</ul>
</div>
<div class="header">
	<h1>Comments</h1>
</div>
<div class="commentSection">
	<?php if(isset($_SESSION['logged-in']) && $_SESSION['logged-in']){ ?>
			<form action="storeComments.php" method="post">
				<textarea class="comment" name="message" placeholder="Leave a comment..." cols="50" rows="5"></textarea>
				<input type="hidden" name="dish" value="meatballs">
				<input type="submit" value="Publish">
			</form>
	<?php }
	$filename = __DIR__ . '/meatballscomments.txt';

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
				echo("<input type='hidden' name='delete' value='meatballs'/>");
				echo("<input type='submit' value='Delete'/>");
				echo("</form>");
			}
		}
	}
	?>
</div>
</body>
</html>