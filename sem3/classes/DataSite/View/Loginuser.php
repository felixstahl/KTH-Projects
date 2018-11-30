<?php
namespace DataSite\View;
	
use Id1354fw\View\AbstractRequestHandler;
use DataSite\Util\Constants;
use DataSite\Controller\Controller;
use DataSite\Model\User;
use DataSite\Integration\loginsession;
	
//lets the user login
class Loginuser extends AbstractRequestHandler{
	private $uname;
	private $pword;
	
	public function setUsername($username){
		$this->uname = $username;
	}
	
	public function setPassword($password){
		$this->pword = $password;
	}	
	
	protected function doExecute(){
		$contr = $this->session->get(Constants::CONTR_KEY_NAME);
		
		$contr->loginUser($this->uname, $this->pword);
		if($contr->getUsername() != 'fel'){
			$this->session->set('username', $contr->getUsername());
		}
		else{
			$incorrect = 'Incorrect username or password, please try again..';
			$this->addVariable('incorrect', $incorrect);
			return 'login';
		}
	
		$this->session->set(Constants::CONTR_KEY_NAME, $contr);
		return 'index';
	}
}
?>