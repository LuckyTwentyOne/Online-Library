;

$(function() {

	function addAuthor() {
		var container = $('#authors');
		var authorSelect = $('.authorSelect:last-child').clone();
		container.append(authorSelect);
	}
	;

	function removeAuthor(event) {
		$(event.target).parent().parent().remove();
	}
	;

	$('#addAuthor').click(addAuthor);

	$('body').on('click', '.removeAuthor', function(event) {
		removeAuthor(event);
	});
	$('#datetimepicker1').datetimepicker({
		language : 'ru'
	});

	setInterval(function() {
		var hours = new Date().getHours();
		$(".hours").html((hours < 10 ? "0" : "") + hours+" :");
	}, 1000);
	setInterval(function() {
		var minutes = new Date().getMinutes();
		$(".min").html((minutes < 10 ? "0" : "") +" "+ minutes);
	}, 1000);
	setInterval(function() {
		var seconds = new Date().getSeconds();
		$(".sec").html((seconds < 10 ? "0" : "") + seconds);
	}, 1000);

});
