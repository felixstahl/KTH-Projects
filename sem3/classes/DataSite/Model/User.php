<?php
namespace DataSite\Model;

class User{
	private $uname;
	private $pword;
	
	public function __construct($username, $password){
		$this->uname = $username;
		$this->pword = $password;
	}
	public function getPassword(){
		return $this->pword;
	}
}
?>