$(document).ready(function(){
	
	// the user logsin
	$("#loginform").on('submit', function(e){
		e.preventDefault();
        loginRequest();
    });
	function loginRequest(){
		$.post("Loginuser", {username: $('#username').val(), password: $('#password').val()}, function(result){
			console.log(result);
                loginResult(result);
		});
	}
	function loginResult(result){
		if(result == 1){
			window.location.assign("Firstpage")
		}else{
			$('#wronglogin').text('Incorrect username or password, please try again..');
		}
    }
	
	// the user logs out
	$("#logoutbtn").on('click', function(e){
		e.preventDefault();
        logoutRequest();
    });
	function logoutRequest(){
		$.post("Logoutuser", function(result){
			console.log(result);
                logoutResult(result);
		});
	}
	function logoutResult(result){
		if(result == 1){
			window.location.assign("Firstpage")
		}else{
			window.location.assign("Firstpage")
			$('#wronglogin').text('Something went wrong with logout, try again..');
		}
    }
});