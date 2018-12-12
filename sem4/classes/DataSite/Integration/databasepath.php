<?php
namespace DataSite\Integration;

use Id1354fw\Util\Classes;
use DataSite\Model\User;
use DataSite\Model\entry;

class databasepath{
	private $accountfile;
	private $meatballcommentfile;
	private $pancakecommentfile;
	private $wrongpword = 'wrong';

	public function __construct() {
		$this->accountfile = $_SERVER['DOCUMENT_ROOT'] . Classes::getContextPath() . 'Database/accounts.txt';
		$this->meatballcommentfile = $_SERVER['DOCUMENT_ROOT'] . Classes::getContextPath() . 'Database/meatballscomments.txt';
		$this->pancakecommentfile = $_SERVER['DOCUMENT_ROOT'] . Classes::getContextPath() . 'Database/pancakescomments.txt';
	}

	public function verifyUser($username, $password){
		$this->accountfile = $_SERVER['DOCUMENT_ROOT'] . Classes::getContextPath() . 'Database/accounts.txt';
		$content = file_get_contents($this->accountfile);
		$content = explode(":", $content);

		foreach($content as $values){
			$loginInfo = explode(",", $values);
			$usernameTrial = $loginInfo[0];
			$passwordTrial = $loginInfo[1];
			if($usernameTrial == $username && $passwordTrial == $password){
				$userDTO = new User($username, $password);
				return $userDTO;
			}
		}
		$userDTO = new User($username, $this->wrongpword);
				return $userDTO;
	}
	
	public function storeComment($entry, $dish){
		if($dish != null && $dish == 'meatballs'){
			file_put_contents($this->meatballcommentfile, serialize($entry) . ";\n", FILE_APPEND);
		}
		else if($dish != null && $dish == 'pancakes'){
			file_put_contents($this->pancakecommentfile, serialize($entry) . ";\n", FILE_APPEND);
		}
	}
	
	public function readComments($file){	
		if($file == 'meatballs'){
			$entry_strings = explode(";\n", \file_get_contents($this->meatballcommentfile));
			$entryarray = array();
			foreach($entry_strings as $entry_string){
				$entry = \unserialize($entry_string);
				if(($entry instanceof entry) && ($entry->isDeleted() != true)){
					$entryarray[] = $entry;
				}
			}
			return $entryarray;
		}
		else if($file == 'pancakes'){
			$entry_strings = explode(";\n", file_get_contents($this->pancakecommentfile));
			$entryarray = array();
			foreach($entry_strings as $entry_string){
				$entry = unserialize($entry_string);
				if(($entry instanceof entry) && (!$entry->isDeleted())){
					$entryarray[] = $entry;
				}
			}
			return $entryarray;
		}
	}
	
	public function deleteComment($timestamp, $filetodelete){
		$file = "";
		if($filetodelete == 'meatballs'){
			$file = $this->meatballcommentfile;
			$entries = explode(";\n", file_get_contents($this->meatballcommentfile));
			for($i = count($entries) - 1; $i >= 0; $i--){
				$entry = unserialize($entries[$i]);
				if($entry instanceof entry && ($entry->getTimestamp() == $timestamp)){
					$entry->setDeleted(true);
					$entries[$i] = serialize($entry);
					break;
				}
			}
		}
		else if($filetodelete == 'pancakes'){
			$file = $this->pancakecommentfile;
			$entries = explode(";\n", file_get_contents($this->pancakecommentfile));
			for($i = count($entries) - 1; $i >= 0; $i--){
				$entry = unserialize($entries[$i]);
				if($entry instanceof entry && ($entry->getTimestamp() == $timestamp)){
					$entry->setDeleted(true);
					$entries[$i] = serialize($entry);
					break;
				}
			}
		}
		file_put_contents($file, implode(";\n", $entries));
	}
}
?>