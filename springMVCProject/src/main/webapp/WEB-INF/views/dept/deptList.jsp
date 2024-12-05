<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 목록 조회</title>
	<script type="text/javascript">
		var resultMessage = "${result}";
		
		if (resultMessage != "") {
			alert(resultMessage);
		}
	</script>
	<style type="text/css">
	.container {
		text-align: center;
		margin-top: 10px;
	}
	
	hr {
		clear: both;
	}
	
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
		padding: 5px;
		text-align: center;
	}
	
	caption {
		caption-side: top;
		text-align: center!important;
		font-size: 2rem;
		font-weight: bold;
	}
	</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp" %>
		<a href="${contextPath}/auth/main.do" class="btn btn-primary">메인 화면으로</a>
		<a href="${contextPath}/dept/insert.do" class="btn btn-success">부서 등록</a>
		<hr>
		
		<table class="table table-striped table-hover table-bordered border-secondary">
			<caption>부서 목록</caption>
			<tr class="table-info">
				<th>부서 번호</th>
				<th>부서 이름</th>
				<th>매니저 번호</th>
				<th>지역 번호</th>
				<th>GET</th>
				<th>POST</th>
			</tr>
			<%-- items : getAttribute("deptList") --%>
			<c:forEach items="${deptList}" var="dept">
				<tr>
					<td><a href="${contextPath}/dept/detail.do?deptid=${dept.dept_id}">${dept.dept_id}</a></td>
					<td>${dept.dept_name}</td>
					<td>${dept.manager_id}</td>
					<td>${dept.location_id}</td>
					<td>
						<button onclick="location.href='${contextPath}/dept/delete.do?deptid=${dept.dept_id}'" class="btn btn-dark">삭제</button>
					</td>
					<td>
						<form action="delete.do" method="post">
							<input type="hidden" name="deptid" value="${dept.dept_id}">
							<input type="submit" value="삭제" class="btn btn-dark">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>