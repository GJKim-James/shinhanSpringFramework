<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>coffee</title>
</head>
<body>
	<h1>coffee.jsp</h1>
	<hr>
	
	<form action="${contextPath}/jsptest2/coffee2.do">
		<input type="number" name="department_id" value="100"><br>
		<input type="text" name="department_name" value="개발부"><br>
		<input type="number" name="manager_id" value="120"><br>
		<input type="number" name="location_id" value="1700"><br>
		<input type="submit" value="서버 전송">
	</form>
	<hr>
	
	<form action="${contextPath}/jsptest2/coffee3.do">
		<input type="number" name="dept_id" value="100"><br>
		<input type="text" name="dept_name" value="개발부"><br>
		<input type="number" name="manager_id" value="120"><br>
		<input type="number" name="location_id" value="1700"><br>
		<input type="submit" value="서버 전송">
	</form>
</body>
</html>