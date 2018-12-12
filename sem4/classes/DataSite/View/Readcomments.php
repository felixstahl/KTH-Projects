<?php
namespace DataSite\View;
	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Util\Constants;	
use DataSite\Controller\Controller;
use DataSite\Model\entry;
	
//read comments
class Readcomments extends AbstractRequestHandler{
	private $recipe;
	
	public function setRecipe($recipe){
		$this->recipe = $recipe;
	}
	
	protected function doExecute(){
		$contr = $this->session->get(Constants::CONTR_KEY_NAME);
		$comments = $contr->readComments($this->recipe);
		
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		echo json_encode($comments);
	}
}
?>