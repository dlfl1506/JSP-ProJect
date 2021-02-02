<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css">
<title>로그인 페이지</title>
</head>

<body>
	<main>
		<a href="/JspProject/index.jsp"> <img
			src=" ../images/loginlogo.png"></a>
	</main>
	<section>
		<form action="/JspProject/user?cmd=login" method="post">
			<div>
				<input type="email" placeholder="이메일" name="email">
			</div>
			<div>
				<input type="password" placeholder="비밀번호" name="password">
			</div>
			<div>
				<button type="submit" class="btn btn-primary">로그인</button>
			</div>
		</form>
		<div class="join">
			<a href="/JspProject/user/joinForm.jsp">회원가입</a>
	</section>


</body>

</html>