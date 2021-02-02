<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<style>

.material-icons{

cursor:pointer;
}
b{
width:600px;
height: 600px;

}
b img{
width:50%;
height:50%;
}
</style>

<main class="detail_box">

<div class="container">
	
	<h3 class="m-2">
		<b><img src="/JspProject/uploads/${dto.photoImage}" ></b>
	</h3>
	<hr />
	<div class="form-group">
		<div class="m-2">${dto.content}</div>
	</div>

	<hr />
<h6 class="m-2">
		작성자 : <i>${dto.userNickname}
		<br></i>조회수 : <i>${dto.readCount}</i>
	</h6>
	
	<c:if test="${sessionScope.principal.id == dto.userId}">
		<button onClick="deleteById(${dto.id})" class="btn btn-danger">삭제</button>
	</c:if>


<br/><br/>
<!-- 댓글 박스 -->
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>댓글</b>
					</div>
					<div class="panel-body">
						<input type="hidden" name="userId"
							value="${sessionScope.principal.id}" /> <input type="hidden"
							name="boardId" value="${dto.id}" />
						<textarea id="content" id="reply__write__form"
							class="form-control" placeholder="write a comment..." rows="2"></textarea>
						<br>

						<button
							onClick="replySave(${sessionScope.principal.id}, ${dto.id})"
							class="btn btn-primary pull-right">댓글쓰기</button>

						<div class="clearfix"></div>
						<hr />

						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
							<c:forEach var="reply" items="${replys}">
								<!-- 댓글 아이템 -->
								<li id="reply-${reply.id}" class="media">
									<div class="media-body">
										<strong class="text-primary">${reply.userNickname}</strong>
										<p>${reply.content}</p>
									</div>
									<div class="m-2">
										<c:if test="${sessionScope.principal.id == reply.userId }">
											<i onclick="deleteReply(${reply.id})" class="material-icons" >delete</i>
										</c:if>
									</div>
								</li>
							</c:forEach>


						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->

</main>

<script src="/JspProject/js/boardDetail.js"></script>
</body>
</html>