$(document).ready(function() {
    $("#content").css("width", "826px");
    $("#page ul li").click(function(){
    	$(".selected_page").removeClass("selected_page");
    	$(this).addClass("selected_page");
    	var page;
    	if($(this).text() != "...") {
    		page = parseInt($(this).text());
    	} else {
    		page = $(this).text();
    	}
    	var lastPage = parseInt($("#page ul li").last().text());
    	var lastPageIndex = $("#page ul li").index($("#page ul li").last());
    	if($(this).next().text() == "..." && page != 1) {
    		$("#page ul li").eq(1).html("...");
    		$("#page ul li").eq(2).html(page - 1);
    		$("#page ul li").eq(3).html(page);
    		$("#page ul li").eq(4).html(page + 1);
    		$(".selected_page").removeClass("selected_page");
    		$(this).prev().addClass("selected_page");
    		if($("#page ul li").last().prev().text() == "..." && (lastPage - parseInt($("#page ul li").last().prev().prev().text())) == 2) {
        		$("#page ul li").last().prev().html(lastPage - 1);
        	}
    	}	
    	if($(this).prev().text() == "..." && page != lastPage) {	
    		$("#page ul li").eq(lastPageIndex - 1).html("...");
    		$("#page ul li").eq(lastPageIndex - 2).html(page + 1);
    		$("#page ul li").eq(lastPageIndex - 3).html(page);
    		$("#page ul li").eq(lastPageIndex - 4).html(page - 1);
    		$(".selected_page").removeClass("selected_page");
    		$(this).next().addClass("selected_page");
    		if($("#page ul li").eq(0).next().text() == "..." && (parseInt($("#page ul li").eq(2).text()) - 1) == 2) {
        		$("#page ul li").eq(1).html(2);
        	}
    	}
    	if(page == "...") {
    		if($("#page ul li").index($(this)) == 1) {
    			page = parseInt($(this).next().text()) - 1;
        		$("#page ul li").eq(2).html(page);
        		$("#page ul li").eq(3).html(page + 1);
        		$("#page ul li").eq(4).html(page + 2);
        		$("#page ul li").eq(5).html("...");
        		$(".selected_page").removeClass("selected_page");
        		$(this).next().addClass("selected_page");
    		} else {
    			page = parseInt($(this).prev().text()) + 1;
        		$("#page ul li").eq(lastPageIndex - 2).html(page);
        		$("#page ul li").eq(lastPageIndex - 3).html(page - 1);
        		$("#page ul li").eq(lastPageIndex - 4).html(page - 2);
        		$("#page ul li").eq(lastPageIndex - 5).html("...");
        		$(".selected_page").removeClass("selected_page");
        		$(this).prev().addClass("selected_page");
    		}
    	}
    	if((page == 1 || page == 3) && $("#page ul li").eq(0).next().text() == "...") {
    		for(i = 1; i <= 4; i++){
    			$("#page ul li").eq(i).html(i + 1);
    		}
    		$("#page ul li").eq(5).html("...");
    	}
    	if((page == lastPage || page == lastPage - 2) && $("#page ul li").eq(lastPageIndex - 1).text() == "...") {
    		for(i = lastPageIndex - 1; i >= lastPageIndex - 4; i--){
    			$("#page ul li").eq(i).html(lastPage - lastPageIndex + i);
    		}
    		$("#page ul li").eq(1).html("...");
    	}
    	$(".active").removeClass("active").addClass("inactive");
    	$("#page_" + page).removeClass("inactive").addClass("active");
    });    
});

function clearAndSubmit(name, value) {
	$(".filter_checkbox").attr('checked', false);
	$('input[name=minPrice]').val($('input[name=lowestPrice]').val());
	$('input[name=maxPrice]').val($('input[name=highestPrice]').val());
	submitForm(name, value);
};

jQuery.fn.addHidden = function(name, value) {
	return this.each(function() {
		var input = $("<input>").attr("type", "hidden").attr("name", name).val(value);
		$(this).append($(input));
	});
};

function submitForm(name, value) {
	$('form[name=filter_form]').addHidden('curMinPrice', $('input[name=minPrice]').val());
	$('form[name=filter_form]').addHidden('curMaxPrice', $('input[name=maxPrice]').val());
	$('form[name=filter_form]').addHidden('orderBy', $('select[name=orderBy]').find(":selected").val());
	$('form[name=filter_form]').addHidden(name, value).submit();
};

function selectOrderBy(option) {
	$('select[name=orderBy]').val(option);
};

