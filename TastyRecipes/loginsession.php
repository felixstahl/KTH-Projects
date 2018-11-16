<?php

$accountfile = "accounts.txt";
$content = file_get_contents($accountfile);
$content = explode(":", $content);

foreach($content as $values){
	$loginInfo = explode(",", $values);
    $username = $loginInfo[0];
    $password = $loginInfo[1];
	if($_POST['username'] == $username && $_POST['password'] == $password){	
		session_start();	
		$_SESSION['username'] = $username;
		$_SESSION['logged-in'] = true;
		header('Location: index.php');
	}
}	
	echo "<script>alert('username or password is incorrect')</script>";
	echo "<script>location.href='loginpage.php'</script>";
?>