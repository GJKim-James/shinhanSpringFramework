<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 조건으로 직원 조회 결과</title>
</head>
<body>	
	${empList}
	<table class="table table-striped table-hover">
		<tr>
			<th>No</th>
			<th>직원 번호</th>
			<th>First Name</th>
			<th>급여</th>
			<th>부서 이름</th>
			<th>도시</th>
			<th>나라</th>
			<th>직책</th>
		</tr>
		<c:forEach items="${empList}" var="emp" varStatus="status">
			<tr>
				<td>${ status.count }</td>
				<td><a href="${contextPath}/emp/detail.do?empid=${emp.employee_id}">${emp.employee_id}</a></td>
				<td>${emp.first_name}</td>
				<td>${emp.salary}</td>
				<td>${emp.department_name}</td>
				<td>${emp.city}</td>
				<td>${emp.country_name}</td>
				<td>${emp.job_title}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>