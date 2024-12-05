<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500 Error</title>
</head>
<body>
	<h1>500 Error 오류 정보</h1>
	<h2>${message}</h2>
	Message : <%=exception.getMessage() %>
</body>
</html>