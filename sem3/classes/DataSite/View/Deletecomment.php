<?php
namespace DataSite\View;
	
use DataSite\Util\Constants;	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Controller\Controller;
use DataSite\Model\entry;
	
//deletes a comment.
class Deletecomment extends AbstractRequestHandler{
	private $timestamp;
	private $filetodelete;
	
	public function setTimestamp($timestamp){
		$this->timestamp = $timestamp;
	}
	public function setFiletodelete($filetodelete){
		$this->filetodelete = $filetodelete;
	}
	
	protected function doExecute(){
		$contr = $this->session->get(Constants::CONTR_KEY_NAME);
		
		$contr->deleteComment($this->timestamp, $this->filetodelete); 
		
		$comments = $contr->readComments($this->filetodelete); 
		$this->addVariable('commentfile', $comments);
		
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		return $this->filetodelete;
	}
}	
?>