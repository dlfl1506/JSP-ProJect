<%@page contentType="text/html; charset=EUC-KR" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.*,java.io.*"%>
<%
	String saveFolder = "C:/mylab/springwork/JspProject/WebContent/uploads";
	String encType = "EUC-KR";
	int maxSize = 5 * 1024 * 1024;
	try {
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, saveFolder, maxSize,
				encType, new DefaultFileRenamePolicy());
	} catch (IOException ioe) {
		System.out.println(ioe);
	} 
%>