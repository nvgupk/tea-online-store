$(document).ready(function() {
    $("#content").css("width", "826px")
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
	$('form[name=filter_form]').addHidden(name, value).submit();
};

