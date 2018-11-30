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
		<img alt="Meatballs" src="../../resources/pictures/meatballs.png"/>
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
	<?php if(($this->session->get('username')) != null){ ?>
			<form action="Addcomment" method="post">
				<textarea class="comment" name="message" placeholder="Leave a comment..." cols="50" rows="5"></textarea>
				<input type="hidden" name="dish" value="meatballs">
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
		echo ("<input type='hidden' name='filetodelete' value='meatballs'/>");
		echo ("<input type='submit' value='Delete'/>");
		echo ("</form>");
		}
	} ?>	
</div>
</body>
</html>