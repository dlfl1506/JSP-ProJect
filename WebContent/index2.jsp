<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
.photo_box {
	margin-left: 200px;
	margin-right: 200px;
	left: 20%;
	right: 25%;
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr;
	grid-gap: 20px;
}

.container {
	width: 250px;
	height: 250px;
}

.container_img {
	width: 230px;
	height: 230px;
}
</style>
<main>

	<h3>오늘의 사진</h3>
	<section class="photo_box">
		<c:forEach var="board" items="${boards}">
			<div class="container">
				<div class="nickname">${board.userNickname }</div>
				<a href=" /JspProject/photo_board?cmd=detail&id=${board.id}"><img
					class="container_img"
					src="/JspProject/uploads/${board.photoImage }" style="width: 100%"></a>
			</div>
		</c:forEach>
	</section>



	<div class="main_img">
		<img src="images/mainimg.png">
	</div>
	<div class="main_categori">
		<img src="images/categori.png">
	</div>
</main>

<footer>
	<img src="images/footer.png">

</footer>



</body>

</html>