<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>메인 화면</title>
<style type="text/css">
li {
	margin: 10px;
}
</style>
</head>
<body>
	<div class="container mt-3">
		<h1>업무 선택</h1>
		<ul>
			<li><a href="${contextPath}/auth/login.do" class="btn btn-secondary">로그인</a></li>
			<li><a href="${contextPath}/emp/list.do" class="btn btn-primary">직원 조회</a></li>
			<li><a href="${contextPath}/emp/insert.do" class="btn btn-success">직원 등록</a></li>
			<li><a href="${contextPath}/dept/list.do" class="btn btn-primary">부서 조회</a></li>
			<li><a href="${contextPath}/dept/insert.do" class="btn btn-success">부서 등록</a></li>
			<li><a href="${contextPath}/board/list.do" class="btn btn-warning">게시판 조회</a></li>
			<li><a href="${contextPath}/board/insert.do" class="btn btn-warning">게시판 등록</a></li>
		</ul>
	</div>
</body>
</html>