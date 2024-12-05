<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 Error</title>
</head>
<body>
	<h1>404 Error 알림 : 주소 확인 필요</h1>
	<p><%=request.getRequestURL().toString() %></p>
	<p id="here"></p>
	
	<script type="text/javascript">
		document.querySelector("#here").innerHTML = location.href;
	</script>
</body>
</html>