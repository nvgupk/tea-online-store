$(document).ready(function() {
    $("#cart_product_list").dialog({
        modal: true,
        autoOpen: false,
        width: 900,
        closeText: "x",
        clickOutside: true,
        clickOutsideTrigger: "#shopping_cart",
        draggable: false,
        resizable: false,
        dialogClass: 'noTitleStuff',
        open: function( event, ui ) {
        	$(".cart_product").remove();
        	var data = "action=getAll";
        	$.ajax({
        		type : "POST",
        		url : "Cart",
        		dataType : "json",
        		data : data,
        		success : function(result) {
        			var totalPrice = 0;
        			for(var i = 0; i < result.length; i++) {
        				console.log(result[i].image);
        				appendProductToCartList(result[i].productId, result[i].name, result[i].price, result[i].image)
        				totalPrice += result[i].price;
        			}
        			if(result.isEmpty || result.length == 0) {
        				$("#total").hide();
        			} else {
        				$("#total span").text(totalPrice);
        				$("#total").show();
        			}
        		},
        		
        		error : function(jqXHR, exception) {
        			console.log("error " + exception + ' Uncaught Error.n ' + jqXHR.responseText);
        		}
        	});
        }
    });
    
    $("#shopping_cart").click(function() {
        $("#cart_product_list").dialog("open");
    });

    $(document).on('input', '.cart_product_number_inp', function() {
        if($(this).val() != '') {
            var re = /^[0-9]+$/;
            var isNumber = re.test($(this).val());
            if(!isNumber) {
                $(this).val('1');
            }
        }
    });

	$(document).on('blur', '.cart_product_number_inp', function() {
        if($(this).val() == '' || $(this).val() == '0' || $(this).val() == '00' || $(this).val() == '000') {
            $(this).val('1');
        }
        var parentId = $(this).parent().parent().attr("id");
        var productNumber = parseInt($(this).val());
        var productPrice = parseInt($('#' + parentId + ' .cart_product_unit_price span').text());
        var totalPriceForUnitProduct = parseInt($('#' + parentId + ' .cart_product_total_price span').text());
        $('#' + parentId + ' .cart_product_total_price span').text(productNumber*productPrice);
        var totalPrice = parseInt( $("#total span").text());
        $("#total span").text(totalPrice - totalPriceForUnitProduct + productNumber*productPrice);
        
    });
    
    $(document).on('click', '.minus_button', function() { 
    	var parentId = $(this).parent().parent().attr("id");
    	console.log(parentId);
        var productNumber = parseInt($('#' + parentId + ' .cart_product_number_inp').val());
        if(productNumber > 1) {
            $('#' + parentId + ' .cart_product_number_inp').val(--productNumber);
            var productPrice = parseInt($('#' + parentId + ' .cart_product_unit_price span').text());
            $('#' + parentId + ' .cart_product_total_price span').text(productNumber*productPrice);
            var totalPrice = parseInt( $("#total span").text());
            $("#total span").text(totalPrice - productPrice);
        }
    });

    $(document).on('click', '.plus_button', function() {
    	var parentId = $(this).parent().parent().attr("id");
        var productNumber = parseInt($('#' + parentId + ' .cart_product_number_inp').val());
        if(productNumber < 999) {
            $('#' + parentId + ' .cart_product_number_inp').val(++productNumber);
            var productPrice = parseInt($('#' + parentId + ' .cart_product_unit_price span').text());
            $('#' + parentId + ' .cart_product_total_price span').text(productNumber*productPrice); 
            var totalPrice = parseInt( $("#total span").text());
            $("#total span").text(totalPrice + productPrice);
        }
    });
    
    $(document).on('click', '.cart_product_delete div', function() {
    	var productId = $(this).parent().parent().attr("id");
    	var data = "productId=" + productId + "&action=remove";
    	$.ajax({
    		type : "POST",
    		url : "Cart",
    		data : data,
    		dataType : "json",
    		success : function(result) {
    			var productsNumber = parseInt($("#cart_count").text());
    			console.log(productsNumber);
    			$("#cart_count").text(--productsNumber);
    			console.log("#" + productId);
    			$("#" + productId).remove();
    			if(productsNumber == 0) {
    				$("#total").hide();
    			}
    		}
    	});
    });
    
    $("#make_purchase").on('click', function() {
    	$(this).prop('disabled', true);
    	var productQuantity = [];
    	$(".cart_product_number_inp").each(function() {
    		productQuantity.push({
    	        productId: $(this).parent().parent().attr("id"),
    	        quantity: $(this).val()
    	    });
    	});
    	var jsonString = JSON.stringify(productQuantity);
    	console.log(jsonString);
    	$.ajax({
    		type : "POST",
    		url : "Cart",
    		data : jsonString,
    		contentType: "application/json; charset=UTF-8",
    		dataType : "json",
    		success : function(result) {
    			window.location = result.url;
    		}
    	});
    });
    
    $('input[name=searchQuery]').on('input', function() {
    	var searchQuery = $(this).val();
    	if(searchQuery == "") {
    		$('#search-result').hide();
    		$(".element").remove();
    		return;
    	}
    	var data = "searchQuery=" + searchQuery;
    	$.ajax({
    		type : "POST",
    		url : "Search",
    		data : data,
    		dataType : "json",
    		success : function(result) {
    			$(".element").remove();
    			if(result.length == 0) {
    				$('#search-result').hide();
    				return;
    			}
    			var n = result.length <= 10 ? result.length : 10;
    			for(var i = 0; i < n; i++) {
    				appendProductToSearchResultList(result[i].productId, result[i].name, result[i].price, result[i].image, result[i].categoryId);
    			}
    			$('#search-result').show();
    		},
    	});
    });
    
    $('input[name=searchQuery]').on('blur', function() {
    	setTimeout(function() { 
    		$('#search-result').hide();
    	}, 100);	
    });
    
    $('input[name=searchQuery]').on('focus', function() {
    	if($('.element').length > 0) {
    		$('#search-result').show();
    	}
    });
});

function addProductToCart(productId, imgBlockId, isBuy) {
	$("#" + imgBlockId + " .cart_button").prop('disabled', true);
	var cart = $('#shopping_cart');
	var imgtodrag = $("#" + imgBlockId).find("img").eq(0);
	if (imgtodrag) {
		var imgclone = imgtodrag.clone().offset({
			top : imgtodrag.offset().top,
			left : imgtodrag.offset().left
		}).css({
			'opacity' : '0.5',
			'position' : 'absolute',
			'height' : '150px',
			'width' : '150px',
			'z-index' : '100'
		}).appendTo($('body')).animate({
			'top' : cart.offset().top + 10,
			'left' : cart.offset().left + 10,
			'width' : 75,
			'height' : 75
		}, 1000, 'easeInOutExpo');

		setTimeout(function() {
			cart.effect("shake", {
				times : 2
			}, 200);
		}, 1500);

		imgclone.animate({
			'width' : 0,
			'height' : 0
		}, function() {
			$(this).detach()
		});
	}

	var data = "productId=" + productId + "&action=add";
	$.ajax({
		type : "POST",
		url : "Cart",
		data : data,
		dataType : "json",
		success : function(result) {
			var productsNumber = result.productsNumberInCart;
			$("#cart_count").text(productsNumber);
			if(isBuy){
				$("#cart_product_list").dialog("open");
			}
		},
		complete : function() {
			$("#" + imgBlockId + " .cart_button").prop('disabled', false);
		}
	});
};

function appendProductToSearchResultList(productId, name, price, image, categoryId) {
	$('#search-result-list').append('' + 
			'<li class="element">' + 
				'<a href="productinfo.jsp?product_id=' + productId + '&category_id=' + categoryId + '">' + 
					'<img src="ImageLoader?image=' + image + '" width="36" height="36">' + 
					'<div class="result-name">' + name + '</div>' +
					'<div class="result-price">' + price + '<br>грн.</div>' + 
				'</a>' + 
			'</li>' + 
			'<div class="clear"></div>');
}

function appendProductToCartList(productId, name, price, image) {
	$("#cart_product_list #cart_labels").after('' +
			'<div class="cart_product" id="' + productId + '">' +
				'<img class="cart_product_img" src="ImageLoader?image=' + image +  '" width="128" height="128">' + 
				'<div class="cart_product_name">' + name + '</div>' +
				'<div class="cart_product_price cart_product_unit_price">' +
					'<span>' + price + '</span> грн.' +
				'</div>' +
				'<div class="cart_product_number">' +
					'<div class="cart_product_number_button minus_button">-</div>' +
					'<input class="cart_product_number_inp" type="text" name="product_number" value="1" maxlength="3">' +
					'<div class="cart_product_number_button plus_button">+</div>' +
				'</div>' +
				'<div class="cart_product_price cart_product_total_price">' +
					'<span>' + price + '</span> грн.' +
				'</div>' +
				'<div class="cart_product_delete">' +
					'<div></div>' +
				'</div>' +
			'</div>');
	
}