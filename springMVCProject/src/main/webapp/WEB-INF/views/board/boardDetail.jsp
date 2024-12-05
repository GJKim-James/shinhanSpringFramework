<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 상세보기 및 수정</title>
	<style type="text/css">
	input[readonly] {
		background-color: #ccc;
	}
	</style>
</head>
<body>
	<div class="container mt-3">
		<%@ include file="../common/header.jsp" %>
		<h2>게시판 상세보기 및 수정</h2>

		<form action="${contextPath}/board/detail.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">게시판 번호</span>
				<input type="number" class="form-control" name="board_no" value="${boardDTO.board_no}" readonly="readonly">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">제목</span>
				<input type="text" class="form-control" name="title" value="${boardDTO.title}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">내용</span>
				<input type="text" class="form-control" name="content" value="${boardDTO.content}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span>
				<input type="text" class="form-control" name="writer" value="${boardDTO.writer}">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">작성일</span>
				<input type="text" class="form-control" name="regdate" value="${boardDTO.regdate}">
			</div>
			
			<div class="input-group mb-3">
				<img alt="${boardDTO.title} 이미지" src="${contextPath}/resources/upload/${boardDTO.pic}"
					 width="100px" height="100px">
			</div>
			
			<c:if test="${loginMember.member_id == boardDTO.writer}">
				<button type="submit" class="btn btn-primary">게시판 수정하기</button>
			</c:if>
		</form>
	</div>
</body>
</html>