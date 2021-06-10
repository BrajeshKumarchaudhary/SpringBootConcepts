/**
 * 
 */

$(document).ready(function() {
	$('#log').click(function(event) {
		var loginUrl = 'http://localhost:6050/api/auth/login';
		var userName = $("#username").val();
		var passWord = $("#password").val();
		var email = $("#email").val();
		var loginData = {
			username : userName,
			password : passWord,
			email : email,
			deviceInfo : {
				deviceId : "12",
				deviceType : "DEVICE_TYPE_ANDROID",
				notificationToken : "Non empty string"
			}
		};
		console.log(loginData);
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : loginUrl,
			data : JSON.stringify(loginData),
			dataType : 'json',
			success : function(data) {
				console.log(data);
				if (data.status == 200) {
					window.location.replace('admin/dashboard');
				} else {
					alert(data);
				}
			},
			error : function(xhr, errorType, exception) {
				var responseText = jQuery.parseJSON(xhr.responseText);
				console.log("ERROR : ", responseText);
				alert(responseText.data);
			}
		});
	});

});