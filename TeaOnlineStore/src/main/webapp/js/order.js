$(document).ready(function() {
	var validEmail = false;
	var validPhoneNumber = false;
	$('.tooltip').tooltipster({
		theme: 'tooltipster-shadow'	                
    });
	
	$(window).mousedown(function() {
	      $('.tooltip').tooltipster('hide');
	});
	
	$('input[name=delivery]').change(function(){
		var deliveryPrice = $(this).next().text();
		var totalOrderPrice = parseFloat($("#order_total span").text());
		var prevDeliveryPrice = $(".prev_deliv_val").next().text();
		if(prevDeliveryPrice) {
			totalOrderPrice -= parseFloat(prevDeliveryPrice);
		}
		if(deliveryPrice) {
			totalOrderPrice += parseFloat(deliveryPrice);
		}
		$("#order_total span").text(totalOrderPrice);
		$(".prev_deliv_val").removeClass("prev_deliv_val");
		$(this).addClass("prev_deliv_val");
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
			} else {
				validEmail = true;
				$("#email_status_img").attr("src","Images/successful.png");
				$("#email_status_img").css("visibility","visible");
				$("input[name=email]").css("border-color","#CCCCCC");
				$('#email_status_img').tooltipster('content', 'e-mail введено вірно');
			}
		} else {
			validEmail = false;
			$("#email_status_img").css("visibility","hidden");
			$("input[name=email]").css("border-color","#CCCCCC");
		}
	});
	
	$('input[name=phonenumber]').blur(function() {
		var phonenumber = $(this).val();
		if(phonenumber == "") {
			$("#phonenumber_status_img").css("visibility","hidden");
			$("input[name=phonenumber]").css("border-color","#CCCCCC");
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
	
	$("#confirm_order").on("click", function(){
		$("#submit_order").trigger("click");
	});
	
	$("form[name=order]").on("submit", function() {
		console.log("Order Form submit");
		$('input[name=email]').trigger('focus');
		$('input[name=email]').trigger('blur');
		$('input[name=phonenumber]').trigger('input');
		console.log(validEmail + " " + validPhoneNumber);
	    if (!validEmail) {
	    	$('#email_status_img').tooltipster('show');
	        return false;
	    }
	    if (!validPhoneNumber) {
	    	$('#phonenumber_status_img').tooltipster('show');
	        return false;
	    }
	    return true;
	});
});