$(document).ready(function(){
	var validEmail = true;
	var validPasswordConfirm = true;
	var validPasswordLength = false;
	var validPhoneNumber = true;
	var validOldPassword = false;

	$('.tooltip').tooltipster({
		theme: 'tooltipster-shadow'	                
    });
	
	$(window).mousedown(function() {
	    $('.tooltip').tooltipster('hide');
	});
	
	$('input[name=old-password]').blur(function() {
		var oldPassword = $(this).val();
		if(oldPassword == "") {
			$("#old_password_status_img").css("visibility","hidden");
			$("input[name=old-password]").css("border-color","#CCCCCC");
			validOldPassword = false;
			return;
		}
		var data = "ajaxOldPassword=" + oldPassword;
		$.ajax({
			type : "POST",
			url : "Personal",
			data : data,
			dataType : "json",
			success : function(result) {
				if(result.isPasswordCorrect) {
					validOldPassword = true;
					$("#old_password_status_img").attr("src","Images/successful.png");
					$("#old_password_status_img").css("visibility","visible");
					$("input[name=old-password]").css("border-color","#CCCCCC");
					$('#old_password_status_img').tooltipster('content', 'Пароль вірний');
				} else {
					validOldPassword = false;
					$("#old_password_status_img").attr("src","Images/unsuccessful.png");
					$("#old_password_status_img").css("visibility","visible");
					$("input[name=old-password]").css("border-color","#CA4437");
					$('#old_password_status_img').tooltipster('content', 'Введений пароль невірний');
				}
			}
		});
	});

	$('input[name=email]').blur(function() {
		var email = $(this).val();
		if(email == "") {
			$("#email_status_img").css("visibility","hidden");
			$("input[name=email]").css("border-color","#CCCCCC");
			validEmail = false;
			return;
		}
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
		var data = "ajaxEmail=" + email;
		$.ajax({
			type : "POST",
			url : "Personal",
			data : data,
			dataType : "json",
			success : function(result) {
				if(result.isEmailOfCurrentUser) {
					validEmail = true;
					$("#email_status_img").css("visibility","hidden");
					$("input[name=email]").css("border-color","#CCCCCC");
					return;
				}
				if(!result.isRegistered) {
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
			validPasswordLength = false;
			validPasswordConfirm = true;
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
			$('#phonenumber_status_img').tooltipster('content', 'Номер вірний');
		} else {
			validPhoneNumber = false;
			$("#phonenumber_status_img").attr("src","Images/unsuccessful.png");
			$("#phonenumber_status_img").css("visibility","visible");
			$("input[name=phonenumber]").css("border-color","#CA4437");
			$('#phonenumber_status_img').tooltipster('content', 'Введіть номер у форматі (+)(XX)XXXXXXXXXX');
		}
	});
	
	$('form[name=personal-form]').ajaxForm({
		dataType:  "json",
        resetForm: false,
        clearForm: false,
		beforeSubmit: function() {
			if (!validEmail) {
				$('#email_status_img').tooltipster('show');
				return false;
			}
			if (!validPasswordConfirm) {
				$('#confPassword_status_img').tooltipster('show');
				return false;
			}
			if (!validPasswordLength && $('input[name=password]').val() != "") {
				$('#password_status_img').tooltipster('show');
				return false;
			}
			if (!validPhoneNumber) {
				$('#phonenumber_status_img').tooltipster('show');
				return false;
			}
			if(!validOldPassword && validPasswordLength) {
				$("#old_password_status_img").attr("src","Images/unsuccessful.png");
				$("#old_password_status_img").css("visibility","visible");
				$("input[name=old-password]").css("border-color","#CA4437");
				$('#old_password_status_img').tooltipster('content', 'Введений пароль невірний');
				$('#old_password_status_img').tooltipster('show');
				return false;
			}
			return true;
		},
		success: function(result) {
			$('input[name=password]').val("");
			$('input[name=confPassword]').val("");
			$('input[name=old-password]').val("");
			if(result.isPasswordCorrect == false) {
				$('#ajax-status-message').addClass('ajax-status-message-error');
				$('#ajax-status-message').text("Зміни не збережено. Пароль невірний");
				$('#ajax-status-message').show();
				setTimeout(function() { 
		    		$('#ajax-status-message').hide();
		    		$('#ajax-status-message').removeClass('ajax-status-message-error');
		    	}, 5000);
				return;
			}
			if(result.isParameterCorrect == false) {
				$('#ajax-status-message').addClass('ajax-status-message-error');
				$('#ajax-status-message').text("Зміни не збережено. Параметри невірні");
				$('#ajax-status-message').show();
				setTimeout(function() { 
		    		$('#ajax-status-message').hide();
		    		$('#ajax-status-message').removeClass('ajax-status-message-error');
		    	}, 5000);
				return;
			}
			$('#ajax-status-message').addClass('ajax-status-message-succ');
			$('#ajax-status-message').text("Зміни збережено");
			$('#ajax-status-message').show();
			setTimeout(function() { 
	    		$('#ajax-status-message').hide();
	    		$('#ajax-status-message').removeClass('ajax-status-message-succ');
	    	}, 5000);
		}

	});
	
	$('#personal-data-item').on('click', function() {
		$('#personal-order').hide();
		$('#personal-data').show();
		$('.active-menu-item').removeClass('active-menu-item');
		$(this).addClass('active-menu-item');
	});
	
	$('#personal-order-item').on('click', function() {
		$('#personal-data').hide();
		$('#personal-order').show();
		$('.active-menu-item').removeClass('active-menu-item');
		$(this).addClass('active-menu-item');
	});
	
	$('.personal-order-info').on('click', function() {
		if($(this).next().is(":visible")){
			$(this).next().hide("slow");
		} else {
			$(this).next().show("slow");;
		}
	});
});