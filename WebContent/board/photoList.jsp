<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>

footer img{
margin-left: 60px;
}


.photolist_main {
	margin-left: 200px;
	margin-right: 200px;
	left: 20%;
	right: 25%;
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
	grid-gap: 20px;
}

.container {
	width: 380px;
	height: 380px;
}

.container_img {
	width: 300px;
	height: 300px;
}

.emoti_box{

display:flex;
justify-content: space-between;
}

</style>

<main class="photolist_main">
	<c:forEach var="board" items="${boards}">
		<div class="container">
			<div class="nickname">${board.userNickname }</div>
			<a href=" /JspProject/photo_board?cmd=detail&id=${board.id}"  onclick="reload();"><img class="container_img"
				src="/JspProject/uploads/${board.photoImage }" style="width: 100%"></a>
			<div class="emoti_box">
				<div>
				<span>👁</span>
				<span>${board.readCount }</span>
				</div> 
				
				<div class="favorite">
				
				</div>
			</div>
			
			<div>
				<c:choose>
					<c:when test="${fn:length(board.content) > 20}">
						<c:out value="${fn:substring(board.content,0,19)}" />....
           </c:when>
					<c:otherwise>
						<c:out value="${board.content }" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:forEach>

</main>

<footer>
	<img src="/JspProject/images/footer.png">

</footer>

<script type="text/javascript">
function reload(){  
       location.reload();
}
</script>
</body>
</html>