<?php
namespace DataSite\View;
	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Util\Constants;
use DataSite\Controller\Controller;
	
//lets the user logout
class Logoutuser extends AbstractRequestHandler{	
	
	protected function doExecute(){
		$contr = $this->session->get(Constants::CONTR_KEY_NAME);
		
		$contr->logoutUser();
		$this->session->set('username', $contr->getUsername());
		
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		return 'index';
	}
}
?>