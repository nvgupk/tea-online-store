$(document).ready(function(){
	$(".attribute").click(function(){
		$(this).next().slideToggle("fast");
	});
	$( "#slider-price" ).slider({
		range:true,
		min: parseFloat($('input[name=lowestPrice]').val()),
		max: parseFloat($('input[name=highestPrice]').val()),
		values: [$('input[name=minPrice]').val(), $('input[name=maxPrice]').val()],
		slide: function(event, ui) {
			$('input[name=minPrice]').val(ui.values[0]);
			$('input[name=maxPrice]').val(ui.values[1]);
		}
	});
});
