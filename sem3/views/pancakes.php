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
<link rel="stylesheet" type="text/css" href="../../resources/css/comment.css">
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
		<img alt="Pancakes" id="pancake" src="../../resources/pictures/pancakes.png"/>
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
	<?php if(($this->session->get('username')) != null){ ?>
			<form action="Addcomment" method="post">
				<textarea class="comment" name="message" placeholder="Leave a comment..." cols="50" rows="5"></textarea>
				<input type="hidden" name="dish" value="pancakes">
				<input type="submit" value="Publish">
			</form>
	<?php }?>
	<?php
	foreach($commentfile as $comment){
		echo ("<p>" . $comment->getUsername() . ": ");
		echo(nl2br($comment->getMessage()));
		if(($this->session->get('username')) != null){
		echo ("</p>");
		echo ("<form action='Deletecomment' method='post'>");
		echo ("<input type='hidden' name='timestamp' value='" . $comment->getTimestamp() . "'/>");
		echo ("<input type='hidden' name='filetodelete' value='pancakes'/>");
		echo ("<input type='submit' value='Delete'/>");
		echo ("</form>");
		}
	} ?>	
</div>
</body>
</html>