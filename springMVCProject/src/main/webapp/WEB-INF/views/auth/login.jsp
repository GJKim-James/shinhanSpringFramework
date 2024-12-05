<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<title>로그인 화면</title>
	<script type="text/javascript">
		<%-- CRUD 작업 후 결과 alert --%>
		var resultMessage = "${result}";
		if (resultMessage != "") {
			alert(resultMessage);
		}
	</script>
	<style type="text/css">
	input[type="submit"] {
		margin: 10px;
	}
	</style>
</head>
<body>
	<h1>Sign In</h1>
	<hr>
	 
	<form action="${contextPath}/auth/login.do" method="post">
		<label>사용자 아이디</label>
		<input type="text" name="userid" value="gjk0635"><br>
		<label>사용자 비밀번호</label>
		<input type="password" name="userpw" value="1234"><br>
		<input type="submit" value="로그인" class="btn btn-dark">
	</form>
</body>
</html>