$(document).ready(function() {
    $("#content").css("width", "826px")
});

function clearAndSubmit(name, value) {
	$(".filter_checkbox").attr('checked', false);
	submitForm(name, value);
};

jQuery.fn.addHidden = function(name, value) {
	return this.each(function() {
		var input = $("<input>").attr("type", "hidden").attr("name", name).val(value);
		$(this).append($(input));
	});
};

function submitForm(name, value) {
	$('form[name=filter_form]').addHidden(name, value).submit();
};

