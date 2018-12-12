$(document).ready(function(){
		readComments();

	// loads all comments on the page
	function readComments(){
		$.ajax({
			url: "Readcomments?recipe="+$("#recipe").text(), 
			success: function(result){
				var comments = JSON.parse(result);
				$('#conversation').empty();
				comments.forEach(function(comment){
					$('#conversation').append("<p>" + comment.user_name + ": " + comment.message + "</p>");
					if(comment.user_name == $("#username").text()){
						$('#conversation').append("<form class='deleteform'>" + 
						"<input id='timestamp' type='hidden' name='timestamp' value='" + comment.timestamp + "'/>" + 
						"<input id='filetodelete' type='hidden' name='filetodelete' value='" + $("#recipe").text() + "'/>" + 
						"<button type='submit'>Delete</button>" + 
						"</form>");
					}
				})
				// delete a comment
				$('.deleteform').on('submit', function(e){
					var timestamp = $(e.currentTarget[0]).val();
					var file = $(e.currentTarget[1]).val();
					$.post("Deletecomment", {timestamp: timestamp, filetodelete: file}, function(){
						readComments();
					});
					e.preventDefault();
				})
			}
		})
	}
	
	
	
	// add a comment
	$('#addcomment').on('submit', function(e){
		e.preventDefault();
		$.post("Addcomment", {message: $('#message').val(), dish: $('#dish').val()}, function(){
			$('#message').val("");
            readComments();
		});
	})
});