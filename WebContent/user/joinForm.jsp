<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입페이지</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/join.css">
</head>

<body>
    <header>
        <a class="logo" href="/JspProject/index.jsp"><img src="../images/loginlogo.png"></a>
        <h3>회원가입</h3>
    </header>

    <main>
    	<form action="/JspProject/user?cmd=join" method="post">
        <div>이메일</div>
        <div>
            <input type="email" placeholder="이메일"  name="email" >
        </div>

        <div>
            비밀번호
        </div>
        <div>
            <input type="password" placeholder="비밀번호"  name="password">
        </div>

        <div>
            별명
        </div>
        <div>
            <input type="text" placeholder="별명" name ="nickname">
        </div>
		
        <button type="submit"  class="btn btn-primary">회원가입 완료</button>
     	</form>
        <div>이미 아이디가 있으신가요? <a href="/JspProject/user/loginForm.jsp">로그인</a></div>


    </main>




</body>

</html>