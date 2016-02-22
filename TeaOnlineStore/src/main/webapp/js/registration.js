$(document).ready(function() {
	var validEmail = false;
	var validPasswordConfirm = false;
	var validPasswordLength = false;
	var validPhoneNumber = false;
	$('.tooltip').tooltipster({
		theme: 'tooltipster-shadow'	                
    });
	
	$(window).mousedown(function() {
	      $('.tooltip').tooltipster('hide');
	});
	
	$('input[name=email]').blur(function() {
		var email = $(this).val();
		if (email != "") {
			var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
			var isEmail = re.test(email);
			if(!isEmail){
				validEmail = false;
				$("#email_status_img").attr("src","Images/unsuccessful.png");
				$("#email_status_img").css("visibility","visible");
				$("input[name=email]").css("border-color","#CA4437");
				$('#email_status_img').tooltipster('content', 'Введіть e-mail у форматі nickname@example.com');
				$('#email_status_img').tooltipster('show');
				return;
			}
			var data = "email=" + email + "&ajax=" + "ajax";
			$.ajax({
				type : "POST",
				url : "Registration",
				data : data,
				dataType : "json",
				success : function(result) {
					if(!result.registrationStatus) {
						validEmail = true;
						$("#email_status_img").attr("src","Images/successful.png");
						$("#email_status_img").css("visibility","visible");
						$("input[name=email]").css("border-color","#CCCCCC");
						$('#email_status_img').tooltipster('content', 'Даний e-mail вільний');
					} else {
						validEmail = false;
						$("#email_status_img").attr("src","Images/unsuccessful.png");
						$("#email_status_img").css("visibility","visible");
						$("input[name=email]").css("border-color","#CA4437");
						$('#email_status_img').tooltipster('content', 'Даний e-mail уже зареєстрований');
						$('#email_status_img').tooltipster('show');
					}
				}
			});
		} else {
			$("#email_status_img").css("visibility","hidden");
			$("input[name=email]").css("border-color","#CCCCCC");
			validEmail = false;
		}
	});
	
	$('input[name=confPassword], input[name=password]').on('input', function() {
		var password = $('input[name=password]').val();
		var confPassword = $('input[name=confPassword').val();
		if(password.length < 4) {
			validPasswordLength = false;
			$("#password_status_img").attr("src","Images/unsuccessful.png");
			$("#password_status_img").css("visibility","visible");
			$("input[name=password]").css("border-color","#CA4437");
			$('#password_status_img').tooltipster('content', 'Введіть пароль довжиною від 4 до 32 символів');
		} else {
			validPasswordLength = true;
			$("#password_status_img").attr("src","Images/successful.png");
			$("#password_status_img").css("visibility","visible");
			$("input[name=password]").css("border-color","#CCCCCC");
			$('#password_status_img').tooltipster('content', 'Пароль надійний');
		}
		if (password != confPassword) {
			validPasswordConfirm = false;
			$("#confPassword_status_img").attr("src","Images/unsuccessful.png");
			$("#confPassword_status_img").css("visibility","visible");
			$("input[name=confPassword]").css("border-color","#CA4437");
			$('#confPassword_status_img').tooltipster('content', 'Паролі не співпадають');
	    } else {
	    	validPasswordConfirm = true;
	    	$("#confPassword_status_img").attr("src","Images/successful.png");
			$("#confPassword_status_img").css("visibility","visible");
			$("input[name=confPassword]").css("border-color","#CCCCCC");
			$('#confPassword_status_img').tooltipster('content', 'Паролі співпали');
	    }
	});
	
	$('input[name=confPassword], input[name=password]').blur(function() {
		var password = $('input[name=password]').val();
		var confPassword = $('input[name=confPassword').val();
		if(password == "" && confPassword == "") {
			$("input[name=confPassword]").css("border-color","#CCCCCC");
			$("input[name=password]").css("border-color","#CCCCCC");
			$("#confPassword_status_img").css("visibility","hidden");
			$("#password_status_img").css("visibility","hidden");
			validPasswordConfirm = false;
			validPasswordLength = false;
		}
	});
	
	$('input[name=phonenumber]').blur(function() {
		var phonenumber = $(this).val();
		if(phonenumber == "") {
			$("#phonenumber_status_img").css("visibility","hidden");
			$("input[name=phonenumber]").css("border-color","#CCCCCC");
			validPhoneNumber = false;
		}
	});
	
	$('input[name=phonenumber]').on('input', function() {
		var phonenumber = $(this).val();
		var re1 = /^\d{10,12}$/;
		var re2 = /^\+?\d{10,12}$/;
		var isPhoneNumber = re1.test(phonenumber) || re2.test(phonenumber);
		if(isPhoneNumber) {
			validPhoneNumber = true;
			$("#phonenumber_status_img").attr("src","Images/successful.png");
			$("#phonenumber_status_img").css("visibility","visible");
			$("input[name=phonenumber]").css("border-color","#CCCCCC");
			$('#phonenumber_status_img').tooltipster('content', 'Номер правильний');
		} else {
			validPhoneNumber = false;
			$("#phonenumber_status_img").attr("src","Images/unsuccessful.png");
			$("#phonenumber_status_img").css("visibility","visible");
			$("input[name=phonenumber]").css("border-color","#CA4437");
			$('#phonenumber_status_img').tooltipster('content', 'Введіть номер у форматі (+)(XX)XXXXXXXXXX');
		}
	});
	
	$("form[name=registration]").on("submit", function() {
	    if (!validEmail) {
	    	$('#email_status_img').tooltipster('show');
	        return false;
	    }
	    if (!validPasswordConfirm) {
	    	$('#confPassword_status_img').tooltipster('show');
	        return false;
	    }
	    if (!validPasswordLength) {
	    	$('#password_status_img').tooltipster('show');
	        return false;
	    }
	    if (!validPhoneNumber) {
	    	$('#phonenumber_status_img').tooltipster('show');
	        return false;
	    }
	    return true;
	});

});