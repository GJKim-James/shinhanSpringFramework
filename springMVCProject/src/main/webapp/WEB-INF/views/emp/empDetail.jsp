<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 정보 상세 보기 및 수정</title>
	<style type="text/css">
	input[required], select[required] {
		background-color: lightyellow;
	}
	
	input[readonly] {
		background-color: #ccc;
	}
	</style>
</head>
<body>
	<div class="container mt-3">
		<%-- include 디렉티브 태그느는 jsp를 합쳐서 컴파일한다. --%>
		<%@ include file="../common/header.jsp" %>
		
		<h2>직원 정보 상세 보기 및 수정</h2>

		<form action="${contextPath}/emp/detail.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">직원 번호</span>
				<input type="number" class="form-control" name="employee_id" value="${empDTO.employee_id}" required="required" readonly="readonly">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">First Name</span>
				<input type="text" class="form-control" name="first_name" value="${empDTO.first_name}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">Last Name</span>
				<input type="text" class="form-control" name="last_name" value="${empDTO.last_name}" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">이메일</span>
				<input type="text" class="form-control" name="email" value="${empDTO.email}" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">전화 번호</span>
				<input type="text" class="form-control" name="phone_number" value="${empDTO.phone_number}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">입사일</span>
				<input type="date" class="form-control" name="hire_date" value="${empDTO.hire_date}" required="required">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">직책</span>
				<select class="form-control" name="job_id" required="required">
					<c:forEach items="${jobList}" var="job">
						<option ${empDTO.job_id == job.job_id ? 'selected' : ''} value="${job.job_id}">${job.job_id} (${job.job_title})</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">급여</span>
				<input type="number" class="form-control" name="salary" value="${empDTO.salary}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">커미션</span>
				<input type="text" class="form-control" name="commission_pct" value="${empDTO.commission_pct}" pattern="[0]\.[0-9]{1,2}">
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
						<option ${empDTO.manager_id == emp.employee_id ? 'selected' : ''} value="${emp.employee_id}">${emp.employee_id} (${emp.first_name} ${emp.last_name})</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">부서 번호</span>
				<select class="form-control" name="department_id">
					<option value="-1">부서 없음</option>
					<c:forEach items="${deptList}" var="dept">
						<option ${empDTO.department_id == dept.dept_id ? 'selected' : ''} value="${dept.dept_id}">${dept.dept_id} (${dept.dept_name})</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn btn-primary">직원 정보 수정</button>
		</form>
	</div>
</body>
</html>