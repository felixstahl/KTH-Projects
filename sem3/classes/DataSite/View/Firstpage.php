<?php
namespace DataSite\View;
	
use Id1354fw\View\AbstractRequestHandler;
	
//shows the index page
class Firstpage extends AbstractRequestHandler{
		
	protected function doExecute(){
		return 'index';
	}
}
?>