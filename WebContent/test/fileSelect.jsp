<%@ page contentType="text/html;charset=EUC-KR"%>
<html>
<body>
<form name="frmName" method="post" enctype="multipart/form-data" 
action="viewPage.jsp">
	user<br/> 
	<input name="user"><br/>
	title<br/> 
	<input name="title"><br/>
	file<br/> 
	<input type="file" name="uploadFile"><br/>
	<input type="submit" value="UPLOAD"><br/>
</form>
</body>
</html>