<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 등록</title>
	<script type="text/javascript">
		window.onload = function() {
			var today = new Date().toISOString().split("T")[0]; // 2024-11-18T02:19:04.489Z => 2024-11-18
			document.querySelector("input[name='hire_date']").value = today;
			
			/* var submit_btn = document.querySelector("button[type='submit']");
			submit_btn.onclick = comm_check; */
		}
		
		/* function comm_check() {
			var comm = document.querySelector("input[name='commission_pct']").value;
			if (comm == '') {
				alert("커미션을 입력해주세요.");
				event.preventDefault();
			}
		} */
	</script>
	<style type="text/css">
	input[required], select[required] {
		background-color: lightyellow;
	}
	</style>
</head>
<body>
	<div class="container mt-3">
		<%-- include 디렉티브 태그는 jsp를 합쳐서 컴파일한다. --%>
		<%@ include file="../common/header.jsp" %>

		<form action="${contextPath}/emp/insert.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">직원 번호</span>
				<input type="number" class="form-control" placeholder="숫자 입력" name="employee_id" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">First Name</span>
				<input type="text" class="form-control" name="first_name" value="James">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">Last Name</span>
				<input type="text" class="form-control" name="last_name" required="required" value="Kim">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">이메일</span>
				<input type="text" class="form-control" name="email" required="required" value="abc@naver.com">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">전화 번호</span>
				<input type="text" class="form-control" name="phone_number" value="010-1234-5678">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">입사일</span>
				<input type="date" class="form-control" name="hire_date" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">직책</span>
				<select class="form-control" name="job_id" required="required">
					<c:forEach items="${jobList}" var="job">
						<option ${job.job_id == 'IT_PROG' ? 'selected' : ''} value="${job.job_id}">${job.job_id} (${job.job_title})</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">급여</span>
				<input type="number" class="form-control" name="salary" value="5000">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">커미션</span>
				<input type="text" class="form-control" name="commission_pct" pattern="[0]\.[0-9]{1,2}">
				<%-- 
				pattern 속성이 제대로 동작하는 <input> 요소의 type 속성값은 다음과 같습니다.
				- date, email, password, search, tel, text, url
				 --%>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">매니저 번호</span>
				<select class="form-control" name="manager_id">
					<option value="-1">매니저 없음</option>
					<c:forEach items="${empList}" var="emp">
						<option ${emp.employee_id == 103 ? 'selected' : ''} value="${emp.employee_id}">${emp.employee_id} (${emp.first_name} ${emp.last_name})</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">부서 번호</span>
				<select class="form-control" name="department_id">
					<option value="-1">부서 없음</option>
					<c:forEach items="${deptList}" var="dept">
						<option value="${dept.dept_id}">${dept.dept_id} (${dept.dept_name})</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn btn-primary">신규 직원 등록</button>
		</form>
	</div>
</body>
</html>