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
			<th>Last Name</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>입사일</th>
			<th>직책</th>
			<th>급여</th>
			<th>커미션</th>
			<th>매니저 번호</th>
			<th>부서 번호</th>
			<th>GET 요청</th>
			<th>POST 요청</th>
		</tr>
		
		<c:forEach items="${empList}" var="emp" varStatus="status">
			<tr>
				<td>
					${ status.count }
					<c:if test="${ status.first }">첫 번째</c:if>
					<c:if test="${ status.last }">마지막</c:if>
					<c:if test="${ status.count mod 2 == 0 }">짝수</c:if>
				</td>
				<td><a href="${contextPath}/emp/detail.do?empid=${emp.employee_id}">${emp.employee_id}</a></td>
				<td>${emp.first_name}</td>
				<td>${emp.last_name}</td>
				<td>
					${ fn:toLowerCase(emp.email) }
				</td>
				<td>
					${ fn:replace(emp.phone_number, ".", "-") }			
				</td>
				<td>${emp.hire_date}</td>
				<td>${emp.job_id}</td>
				<td>
					<fmt:setLocale value="en_US"/>
					<fmt:formatNumber type="currency" value="${emp.salary}"/>
				</td>
				<td>${emp.commission_pct}</td>
				<td>${emp.manager_id}</td>
				<td>${emp.department_id}</td>
				
				<td><button onclick="location.href='${contextPath}/emp/delete.do?empid=${emp.employee_id}'" class="btn btn-dark">삭제</button></td>
				<td>
					<form action="${contextPath}/emp/delete.do" method="post">
						<input type="hidden" name="empid" value="${emp.employee_id}">
						<button class="btn btn-dark">삭제</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>