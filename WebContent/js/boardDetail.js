function addReply(data){
	
	var replyItem = `<li id="reply-${data.id}" class="media">`;
	replyItem += `<div class="media-body">`;
	replyItem += `<strong class="text-primary">${data.userNickname}</strong>`;
	replyItem += `<p>${data.content}</p></div>`;
	replyItem += `<div class="m-2">`;
	
	replyItem += `<i onclick="deleteReply(${data.id})" class="material-icons">delete</i></div></li>`;
	
	$("#reply__list").prepend(replyItem);
}

function deleteReply(id){
	// 세션의 유저의 id와 reply의 userId를 비교해서 같을때만!!
	$.ajax({
		type : "post",
		url : "/JspProject/reply?cmd=delete&id="+id,
		dataType : "json"
	}).done(function(result) { //  { "statusCode" : 1 }
		if (result.statusCode == 1) {
			console.log(result);
			$("#reply-"+id).remove();
		} else {
			alert("댓글삭제 실패");
		}
	});
}

function replySave(userId, boardId) {

	var data = {
		userId : userId,
		boardId : boardId,
		content : $("#content").val()
	}

	$.ajax({
		type : "post",
		url : "/JspProject/reply?cmd=save",
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	}).done(function(result) {
		if (result.statusCode == 1) {
			console.log(result);
			addReply(result.data);
			$("#content").val("");
			//location.reload();
		} else {
			alert("댓글쓰기 실패");
		}
	});
}


function deleteById(boardId){

	$.ajax({
		type: "post",
		url: "/JspProject/photo_board?cmd=delete&id="+boardId,
		dataType: "json"
	}).done(function(result){
		console.log(result);
		if(result.statusCode == 1){
			location.href="/JspProject/photo_board?cmd=photolist";
		}else{
			alert("삭제에 실패하였습니다.");
		}
	});
}