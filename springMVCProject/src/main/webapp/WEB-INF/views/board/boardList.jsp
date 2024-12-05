<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<div class="container mt-3">
		<%@ include file="../common/header.jsp"%>
		<a href="${contextPath}/auth/main.do" class="btn btn-primary">메인 화면으로</a>
		<hr>
		
		<img alt="우산 이미지" src="${contextPath}/resources/image/umbrella.jpg" width="150px" height="100px">
		<hr>
		
		<table class="table table-striped table-hover">
			<caption>게시판 목록</caption>
			<tr>
				<th>SEQ</th>
				<th>게시판 번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>이미지</th>
				<th></th>
			</tr>

			<c:forEach items="${boardList}" var="board" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><a href="${contextPath}/board/detail.do?boardNo=${board.board_no}">${board.board_no}</a></td>
					<td>${board.title}</td>
					<td>${board.content}</td>
					<td>${board.writer}</td>
					<td>${board.regdate}</td>
					<td>${board.pic}</td>
					<td>
						<c:if test="${loginMember.member_id == board.writer}">
							<button onclick="location.href='${contextPath}/board/delete.do?boardNo=${board.board_no}'"
									class="btn btn-dark">삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>