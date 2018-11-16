<?php

//Deletes the specified comment from either the meatballs or pancakes page.
require_once 'entry.php';

session_start();
if(!empty($_GET['timestamp'])) {
	if(isset($_GET['delete']) && $_GET['delete'] == 'meatballs'){
		$meatballcomments = __DIR__ . '/meatballscomments.txt';
		$entries = explode(";\n", file_get_contents($meatballcomments));
		for($i = count($entries) - 1; $i >= 0; $i--) {
			$entry = unserialize($entries[$i]);
			if($entry instanceof entry and ($entry->getTimestamp() == $_GET['timestamp'])) {
				$entry->setDeleted(true);
				$entries[$i] = serialize($entry);
				break;
			}
		}
		file_put_contents($meatballcomments, implode(";\n", $entries));
		include 'meatballs.php';
	}
	
	if(isset($_GET['delete']) && $_GET['delete'] == 'pancakes'){
		$pancakecomments = __DIR__ . '/pancakescomments.txt';
		$entries = explode(";\n", file_get_contents($pancakecomments));
		for($i = count($entries) - 1; $i >= 0; $i--) {
			$entry = unserialize($entries[$i]);
			if($entry instanceof entry and ($entry->getTimestamp() == $_GET['timestamp'])) {
				$entry->setDeleted(true);
				$entries[$i] = serialize($entry);
				break;
			}
		}
		file_put_contents($pancakecomments, implode(";\n", $entries));
		include 'pancakes.php';
	}
}
?>
