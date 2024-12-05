<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first1</title>
</head>
<body>
	<h1>first1.jsp</h1>
	<hr>
	
	<h2>POST 요청 연습</h2>
	<form action="${contextPath}/second3.do" method="POST">
		id : <input type="text" name="userid" value="gjk0635"><br>
		pw : <input type="text" name="userpw" value="1234"><br>
		<input type="submit" value="서버에 전송(post)">
	</form>
	<hr>
	
	<h2>요청 파라미터 체크 연습</h2>
	<form action="${contextPath}/second4.do" method="GET">
		id : <input type="text" name="userid" value="gjk0635"><br>
		pw : <input type="text" name="userpw" value="1234"><br>
<!-- 		email : <input type="email" name="email" value="gjk0635@naver.com"><br> -->
		<input type="submit" value="서버에 전송(post)">
	</form>
</body>
</html>