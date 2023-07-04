<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= request.getParameter("memberName") %> 님 가입 결과</title>
</head>
<body>
	<ul>
		<li>아이디: <%= request.getParameter("memberId") %></li>
		<li>비밀번호: <%= request.getParameter("memberPw") %></li>
		<li>이름: <%= request.getParameter("memberName") %></li>
		<li>자기 소개: <%= request.getParameter("intro") %></li>
	</ul>

	<h1> <%= (String)request.getAttribute("msg") %></h1>
</body>
</html>