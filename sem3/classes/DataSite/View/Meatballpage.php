<?php
namespace DataSite\View;
	
use DataSite\Util\Constants;	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Controller\Controller;
use DataSite\Model\entry;
	
//shows the meatball page
class Meatballpage extends AbstractRequestHandler{
	
	protected function doExecute(){
		$contr = $this->session->get(Constants::CONTR_KEY_NAME);
		
		$file = 'meatballs';
		$comments = $contr->readComments($file); 
		
		$this->addVariable('commentfile', $comments);
		
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		return 'meatballs';
	}
}	
?>