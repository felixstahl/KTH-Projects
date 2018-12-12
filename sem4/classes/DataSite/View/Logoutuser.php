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
		
		if(($contr->getUsername() == null) && ($contr->getUserDTO() == null)){
			$this->session->set(Constants::CONTR_KEY_NAME, $contr);
			echo "1";
			return 1;		
		}
		
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		echo "0";
		return 0;
	}
}
?>