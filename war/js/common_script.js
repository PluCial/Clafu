//もっと読む 開始
function read_more_start() {
	$('#contents-list .readNext ul.actions').css({"display":"none"});
	$('#contents-list .readNext img').css({"display":"inline"});
}

//もっと読む 開始
function read_more_reset() {
	$('#contents-list div.readNext img').css({"display":"none"});
	$('#contents-list div.readNext ul.actions').css({"display":"block", "display":"-moz-box", "display":"-webkit-box"});
}


//もっと読む
function read_more_content(href) {
	read_more_start();
	$.ajax({
		type : 'GET',
		url : href,
		dataType : 'html',
		timeout : 1000000000,
		success : function(result) {
			$("#contents-list .readNext").remove();
			$('#contents-list').append(result);
		},
		error : function() {
			read_more_reset();
		}
	});
};