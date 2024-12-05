<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 등록</title>
</head>
<body>
	<div class="container mt-3">
		<%@ include file="../common/header.jsp" %>
		<h2>부서 등록</h2>

		<form action="${contextPath}/dept/insert.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">부서 번호</span>
				<input type="number" class="form-control" placeholder="숫자 입력" name="dept_id" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">부서 이름</span>
				<input type="text" class="form-control" name="dept_name" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">매니저 번호</span>
				<input type="number" class="form-control" name="manager_id" value="105">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">지역 번호</span>
				<input type="number" class="form-control" name="location_id" value="1700">
			</div>

			<button type="submit" class="btn btn-primary">신규 부서 등록</button>
			<button type="button" class="btn btn-danger" id="btn_ajax">신규 부서 등록(Ajax)</button>
		</form>
	</div>
	<script type="text/javascript">
		$("#btn_ajax").on("click", f_jsonInsert);
		
		function f_jsonInsert() {
			var obj = {
					"deptid" : $('[name="department_id"]').val(),
					"deptname" : $('[name="department_name"]').val(),
					"managerid" : $('[name="manager_id"]').val(),
					"locationid" : $('[name="location_id"]').val()
			};
			console.log(obj);
			
			/* obj를 JSON 문자로 변환 */
			var jsonStr = JSON.stringify(obj);
			console.log(jsonStr);
			
			$.ajax({
				url: "${path}/json.do",
				type: "GET",
				data: { "jsonInfo" : jsonStr },
				success: function (responseData) {
					alert(responseData);
				},
				error: function(err) {
					alert(err);
				},
				complete: function() {
					alert("신규 부서 등록 Ajax 완료!");
				}
			});
		}
	</script>
</body>
</html>