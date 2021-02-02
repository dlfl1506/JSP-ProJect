<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
RequestDispatcher dis = request.getRequestDispatcher("photo_board?cmd=index");
dis.forward(request, response);
// response.sendRedirect("photo_board?cmd=index");
%>