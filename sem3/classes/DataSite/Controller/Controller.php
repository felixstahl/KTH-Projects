<?php
namespace DataSite\Controller;

use DataSite\View\logoutuser;
use DataSite\Integration\loginsession;
use DataSite\Integration\databasepath;
use DataSite\Integration\storeComments;
use DataSite\Model\User;
use DataSite\Model\entry;

// this is the controller of the application. All calls pass through here
class Controller {
	private $currentuser;
	private $database;
	private $userDTO;
	
	public function __construct(){
		$this->database = new databasepath();
	}
	
	public function loginUser($username, $password){
		$this->userDTO = $this->database->verifyUser($username, $password);
		if($this->userDTO->getPassword() == $password){
			$this->currentuser = $username;
		}
		else{
			$this->currentuser = 'fel';
		}
	}
	public function getUsername(){
		return $this->currentuser;
	}
	
	public function logoutUser(){
		$this->currentuser = null;
		$this->userDTO = null;
	}
	
	public function storeComment($message, $dish){
		if(!empty($message)){
			$newentry = new entry($this->currentuser, $message);
			$this->database->storeComment($newentry, $dish);
		}
	}
	public function readComments($file){
		$comments = $this->database->readComments($file);
		return $comments;
	}
	
	public function deleteComment($timestamp, $filetodelete){
		$this->database->deleteComment($timestamp, $filetodelete);
	}
}
?>