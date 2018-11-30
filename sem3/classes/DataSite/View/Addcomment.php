<?php
namespace DataSite\View;
	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Controller\Controller;
use DataSite\Util\Constants;
	
//adds a comment to which ever page you commented on
class Addcomment extends AbstractRequestHandler{
	private $message;
	private $dish;
	
	public function setMessage($message){
		$this->message = htmlentities($message, ENT_QUOTES);

	}
	
	public function setDish($dish){
		$this->dish = $dish;
	}
	
	protected function doExecute(){
		$contr = $this->session->get(Constants::CONTR_KEY_NAME);
		
		if(empty($this->message)){
			$errormessage = 'We dont accept empty comments, please try again..';
			$this->addVariable('error', $errormessage);
			
			$comments = $contr->readComments($this->dish); 
			$this->addVariable('commentfile', $comments);
			
			$this->session->set(Constants::CONTR_KEY_NAME, $contr);
			return $this->dish;
		}
		
		$contr->storeComment($this->message, $this->dish);
		$comments = $contr->readComments($this->dish); 
		
		$this->addVariable('commentfile', $comments);
		
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		return $this->dish;
	}
}	
?>