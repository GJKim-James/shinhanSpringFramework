<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 목록</title>
	<style type="text/css">
	.container {
		text-align: center;
		margin-top: 10px;
	}
	
	hr {
		clear: both;
	}
	
	.input-group
	</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp" %>
		<a href="${contextPath}/auth/main.do" class="btn btn-primary">메인 화면으로</a>
		<hr>
		
		<button id="btnSalary" class="btn btn-danger">급여로만 조회</button>
		<button id="btnJob" class="btn btn-danger">직책으로만 조회</button>
		<button id="btnDept" class="btn btn-danger">부서로만 조회</button>
		<button id="btnJobJoin" class="btn btn-danger">직책Join(List) 조회</button>
		<button id="btnJobJoinMap" class="btn btn-danger">직책Join(Map) 조회</button>
		<button id="btnArray" class="btn btn-danger">부서 배열 조회</button>
		<button id="btnTransfer" class="btn btn-danger">Transaction 연습</button>
		<hr>
		
		<h1>Restful API 사용하기</h1>
		<button id="btnSelect" class="btn btn-secondary">전체조회</button>
		<button id="btnInsert" class="btn btn-secondary">입력(jsp에 값 고정)</button>
		<button id="btnUpdate" class="btn btn-secondary">수정(jsp에 값 고정)</button>
		<div class="input-group mt-3 mb-3" style="width: 300px; margin: 0 auto;">
			<input type="number" id="empid" value="100" class="form-control">
			<button id="btnDetail" class="btn btn-secondary">상세보기</button>
		</div>
		<div class="input-group mt-3" style="width: 300px; margin: 0 auto;">
			<input type="number" id="empid2" value="1" class="form-control">
			<button id="btnDelete" class="btn btn-secondary">삭제</button>
		</div>
		<hr>
		
		<div>
			<fieldset>
				<div class="input-group mt-5 mb-5">
					<legend>조건 선택</legend>
					<span class="input-group-text">부서 번호</span>
					<select class="form-control" name="department_id">
						<option value="-1">선택 안함</option>
						<c:forEach items="${deptList}" var="dept">
							<option value="${dept.dept_id}">${dept.dept_id} (${dept.dept_name})</option>
						</c:forEach>
					</select>
					
					<span class="input-group-text">직책</span>
					<select class="form-control" name="job_id" required="required">
						<option value="-1">선택 안함</option>
						<c:forEach items="${jobList}" var="job">
							<option ${job.job_id == 'IT_PROG' ? 'selected' : ''} value="${job.job_id}">${job.job_id} (${job.job_title})</option>
						</c:forEach>
					</select>
					
					<span class="input-group-text">급여(이상)</span>
					<input type="number" class="form-control" name="salary" value="5000">
					
					<span class="input-group-text">입사일(이후)</span>
					<input type="date" class="form-control" name="hire_date">
					<div class="input-group-text">
						<input class="form-check-input mt-0" type="checkbox" value="1" name="chkDate">모든 일자
					</div>
					<button id="btn_condition" class="btn btn-success">조건 조회</button>
				</div>
			</fieldset>
		</div>
		<hr>
		
		<h1>직원 목록</h1>
		<%-- ${empDatas} --%>
		
		<div id="table_here">
<!-- 			<table class="table table-striped table-hover"> -->
<!-- 				<tr> -->
<!-- 					<th>직원 번호</th> -->
<!-- 					<th>First Name</th> -->
<!-- 					<th>Last Name</th> -->
<!-- 					<th>이메일</th> -->
<!-- 					<th>전화번호</th> -->
<!-- 					<th>입사일</th> -->
<!-- 					<th>직책</th> -->
<!-- 					<th>급여</th> -->
<!-- 					<th>커미션</th> -->
<!-- 					<th>매니저 번호</th> -->
<!-- 					<th>부서 번호</th> -->
<!-- 					<th>GET 요청</th> -->
<!-- 					<th>POST 요청</th> -->
<!-- 				</tr> -->
				
<%-- 				<c:forEach items="${empDatas}" var="emp"> --%>
<!-- 					<tr> -->
<%-- 						<td><a href="${path}/empdetail.do?empid=${emp.employee_id}">${emp.employee_id}</a></td> --%>
<%-- 						<td>${emp.first_name}</td> --%>
<%-- 						<td>${emp.last_name}</td> --%>
<%-- 						<td>${emp.email}</td> --%>
<%-- 						<td>${emp.phone_number}</td> --%>
<%-- 						<td>${emp.hire_date}</td> --%>
<%-- 						<td>${emp.job_id}</td> --%>
<%-- 						<td>${emp.salary}</td> --%>
<%-- 						<td>${emp.commission_pct}</td> --%>
<%-- 						<td>${emp.manager_id}</td> --%>
<%-- 						<td>${emp.department_id}</td> --%>
						
<%-- 						<td><button onclick="location.href='${path}/empdelete.do?empid=${emp.employee_id}'" class="btn btn-dark">삭제</button></td> --%>
<!-- 						<td> -->
<%-- 							<form action="${path}/empdelete.do" method="post"> --%>
<%-- 								<input type="hidden" name="empid" value="${emp.employee_id}"> --%>
<!-- 								<button class="btn btn-dark">삭제</button> -->
<!-- 							</form> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</table> -->
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			// Restful API 사용
			$("#btnSelect").on("click", f_select);
			$("#btnDetail").on("click", f_detail);
			$("#btnInsert").on("click", f_insert);
			$("#btnUpdate").on("click", f_update);
			$("#btnDelete").on("click", f_delete);
		});
		
		function print(res) {
			var dynamicRows = "";
			$.each(res, function(index, emp) {
				dynamicRows += `
					<tr>
						<td>\${ index + 1 }</td>
						<td><a href="${contextPath}/emp/detail.do?empid=\${emp.employee_id}">\${emp.employee_id}</a></td>
						<td>\${emp.first_name}</td>
						<td>\${emp.last_name}</td>
						<td>\${emp.email}</td>
						<td>\${emp.phone_number}</td>
						<td>\${emp.hire_date}</td>
						<td>\${emp.job_id}</td>
						<td>\${emp.salary}</td>
						<td>\${emp.commission_pct}</td>
						<td>\${emp.manager_id}</td>
						<td>\${emp.department_id}</td>
					</tr>
				`
			});
			
			var output = `
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
					</tr>
					
					\${dynamicRows}
				</table>
			`
			
			return output;
		}
		
		// Restful API 사용
		function f_select() {
			$.ajax({
				url: "${contextPath}/rest/emplist.do",
				type: "GET",
				success: function (res) {
					console.log(res);
					
					var output = print(res);
					
					$("#table_here").html(output);
				},
				error: function (err) {
					alert(err);
				}
			});
		}
	
		function f_detail() {
			var empid = $("#empid").val();
			
			$.ajax({
				url: `${contextPath}/rest/empdetail.do/\${empid}`,
				type: "GET",
				success: function (res) {
					console.log(res);
					
					var dynamicOutput = "";
					for (let prop in res) {
						dynamicOutput += `<li>\${prop}의 값은 \${res[prop]}</li>`;
					}
					var output = `<ul>\${dynamicOutput}</ul>`;
					
					$("#table_here").html(output);
				},
				error: function (err) {
					alert(err);
				}
			});
		}
		
		function getData() {
			var obj = {
					  "employee_id": 2,
					  "first_name": "GwangJin33333",
					  "last_name": "Kim33333",
					  "email": "abc33333@naver.com",
					  "phone_number": "010-1111-3333",
					  "hire_date": 1733151600000,
					  "job_id": "IT_PROG",
					  "salary": 4000,
					  "commission_pct": 0.1,
					  "manager_id": 103,
					  "department_id": 60
					};
			
			return obj;
		}
		
		function f_insert() {
			var emp = getData();
			
			$.ajax({
				url: "${contextPath}/rest/empinsert.do",
				type: "POST",
				contentType: "application/json", // default : application/x-www-form-urlencoded
				data: JSON.stringify(emp),
				success: function(res) {
					alert(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
	
		function f_update() {
			var emp = getData();
			
			$.ajax({
				url: "${contextPath}/rest/empupdate.do",
				type: "PUT",
				contentType: "application/json", // default : application/x-www-form-urlencoded
				data: JSON.stringify(emp),
				success: function(res) {
					alert(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
	
		function f_delete() {
			var empid = $("#empid2").val();
			
			$.ajax({
				url: `${contextPath}/rest/empdelete.do/\${empid}`,
				type: "DELETE",
				success: function (res) {
					alert(res);
				},
				error: function (err) {
					alert(err);
				}
			});
		}
	</script>
	
	<script type="text/javascript">
		<%-- CRUD 작업 후 결과 alert --%>
		var resultMessage = "${result}";
		if (resultMessage != "") {
			alert(resultMessage);
		}
		
		$(function () {
			var date = new Date();
			date.setFullYear(date.getFullYear() - 30);
			$('[name="hire_date"]').val(date.toISOString().split("T")[0]);
			
			$("#btn_condition").on("click", f_ajax);
			
			// 이벤트 호출; 화면이 로드되자마자 디폴트로 값을 보여주기 위함(아무것도 없는 화면이 싫어서)
			$("#btn_condition").trigger("click");
			
			// 급여로만 조회
			$("#btnSalary").on("click", f_salary);
			$("#btnJob").on("click", f_job);
			$("#btnDept").on("click", f_dept);
			$("#btnJobJoin").on("click", f_jobjoin);
			$("#btnJobJoinMap").on("click", f_jobjoinmap);
			$("#btnArray").on("click", f_deptArray);
			$("#btnTransfer").on("click", f_transfer);
		});
		
		function f_ajax() {
			$.ajax({
				url: "${contextPath}/emp/list2.do",
				type: "GET",
				data: {
					"department_id": $('[name="department_id"]').val(),
					"job_id": $('[name="job_id"]').val(),
					"salary": $('[name="salary"]').val(),
					"hire_date": $('[name="hire_date"]').val(),
					"chkdate": $('[name="chkDate"]').prop("checked") // true, false
					},
				success: function (responseData) {
					$("#table_here").html(responseData);
				},
				error: function (err) {
					alert(err);
				}
			});
		}
		
		function f_salary() {
			$.ajax({
				url: "${contextPath}/emp/listBySalary.do",
				type: "get",
				data: {salary : $("input[name='salary']").val()},
				success: function(res) {
					$("#table_here").html(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
		
		function f_job() {
			$.ajax({
				url: "${contextPath}/emp/listByJobId.do",
				type: "get",
				data: {job_id : $("select[name='job_id']").val()},
				success: function(res) {
					$("#table_here").html(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
		
		function f_dept() {
			$.ajax({
				url: "${contextPath}/emp/listByDeptId.do",
				type: "get",
				data: {department_id : $("select[name='department_id']").val()},
				success: function(res) {
					$("#table_here").html(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
		
		function f_jobjoin() {
			$.ajax({
				url: "${contextPath}/emp/listByJobJoin.do",
				type: "get",
				data: {job_id : $("select[name='job_id']").val()},
				success: function(res) {
					$("#table_here").html(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
		
		function f_jobjoinmap() {
			$.ajax({
				url: "${contextPath}/emp/listByJobJoinMap.do",
				type: "get",
				data: {job_id : $("select[name='job_id']").val()},
				success: function(res) {
					$("#table_here").html(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
		
		function f_deptArray() {
			$.ajax({
				url: "${contextPath}/emp/listByArray.do",
				type: "get",
				data: {deptArr : [10, 60, 90]},
				success: function(res) {
					$("#table_here").html(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
		
		function f_transfer() {
			$.ajax({
				url: "${contextPath}/account/transfer.do",
				type: "get",
				success: function(res) {
					alert(res);
				},
				error: function(err) {
					alert(err);
				}
			});
		}
	</script>
</body>
</html>