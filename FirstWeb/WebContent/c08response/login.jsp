<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");

	String name = "이강인";
	String eName = URLEncoder.encode(name, "utf-8");
	/* 한글 처리시: URLEncoder.encode() - URLDecoder.decode()  */
	if (id != null && id.equals("minhee123")) {
		response.sendRedirect("view.jsp?name=" + eName);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style></style>
</head>
<body>
	<h1>인증처리된 아이디가 아닙니다.</h1>
</body>
</html>