<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
</head>
<body>
	<div class="container mt-3">
		<%@ include file="../common/header.jsp"%>
		<a href="${contextPath}/auth/main.do" class="btn btn-primary">메인 화면으로</a>

		<form action="${contextPath}/board/insert.do" method="post" enctype="multipart/form-data">
			<div class="input-group mb-3">
				<span class="input-group-text">제목</span>
				<input type="text" class="form-control" name="title">
			</div>

			<div class="input-group mb-3">
				<span class="input-group-text">내용</span>
				<input type="text" class="form-control" name="content">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">이미지 선택</span>
				<input type="file" class="form-control" name="pic">
			</div>
			
			<div class="input-group mb-3">
				<input type="submit" class="btn btn-primary" value="등록하기">
			</div>
		</form>
	</div>
</body>
</html>