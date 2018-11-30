<?php
namespace DataSite\View;
	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Controller\Controller;
use DataSite\Util\Constants;
	
//shows the index page
class DefaultRequestHandler extends AbstractRequestHandler{
		
	protected function doExecute(){
		$this->session->restart();
		$this->session->set(Constants::CONTR_KEY_NAME, new Controller());
		\header('Location: /applikationer1/DataSite/View/Firstpage');
	}
}
?>