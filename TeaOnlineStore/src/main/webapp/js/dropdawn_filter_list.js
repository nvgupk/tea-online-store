$(document).ready(function(){
	$(".attribute").click(function(){
		$(this).next().slideToggle("fast");
	});
});