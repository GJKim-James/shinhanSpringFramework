<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 상세보기 및 수정</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-3">
		<h2>부서 상세보기 및 수정</h2>

		<form action="${contextPath}/dept/detail.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">부서 번호</span>
				<input type="number" class="form-control" name="dept_id" value="${deptDTO.dept_id}" readonly="readonly">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">부서 이름</span>
				<input type="text" class="form-control" name="dept_name" value="${deptDTO.dept_name}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">매니저 번호</span>
				<input type="number" class="form-control" name="manager_id" value="${deptDTO.manager_id}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">지역 번호</span>
				<input type="number" class="form-control" name="location_id" value="${deptDTO.location_id}">
			</div>
			
			<button type="submit" class="btn btn-primary">부서 수정하기</button>
		</form>
	</div>
</body>
</html>