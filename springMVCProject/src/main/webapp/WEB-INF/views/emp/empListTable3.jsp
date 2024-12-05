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
		
		<%-- List<Map<String, Object>> empList 결과가 아래처럼 나옴
		[{DEPARTMENT_NAME=Executive, EMPLOYEE_ID=100, SALARY=24000, CITY=Seattle, COUNTRY_NAME=United States of America, FIRST_NAME=Steven}]
		${emp.EMPLOYEE_ID} 이렇게 Map의 키 값을 대문자로 입력해줘야 함.
		--%>
		<c:forEach items="${empList}" var="emp" varStatus="status">
			<tr>
				<td>${ status.count }</td>
				<td><a href="${contextPath}/emp/detail.do?empid=${emp.EMPLOYEE_ID}">${emp.EMPLOYEE_ID}</a></td>
				<td>${emp.FIRST_NAME}</td>
				<td>${emp.SALARY}</td>
				<td>${emp.DEPARTMENT_NAME}</td>
				<td>${emp.CITY}</td>
				<td>${emp.COUNTRY_NAME}</td>
				<td>${emp.JOB_TITLE}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>