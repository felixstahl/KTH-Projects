<?php

//Stores a new entry in the comments file.
require_once 'entry.php';

$meatballcomments = __DIR__ . '/meatballscomments.txt';
$pancakecomments = __DIR__ . '/pancakescomments.txt';

session_start();
if(!empty($_POST['message'])){
	$entry = new entry($_SESSION['username'], $_POST['message']);
	if(isset($_POST['dish']) && $_POST['dish'] == 'meatballs'){
		file_put_contents($meatballcomments, serialize($entry) . ";\n", FILE_APPEND);
		header('Location: meatballs.php');
	}
	else if(isset($_POST['dish']) && $_POST['dish'] == 'pancakes'){
		file_put_contents($pancakecomments, serialize($entry) . ";\n", FILE_APPEND);
		header('Location: pancakes.php');
	}
}
?>