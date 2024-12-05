<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- 반복문 사용할 수 있게 해줌 --%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
#loginInfo {
	background-color: beige;
	border: 1px dotted gray;
	border-radius: 10px;
	width: 300px;
	float: right;
	margin-bottom: 10px;
	text-align: center;
}

a {
	margin-left: 10px;
}

strong {
	text-decoration: underline;
}

.container {
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
<div id="loginInfo">
	<c:if test="${loginMember.member_name == null}">
		손님이시군요
	</c:if>
	<c:if test="${loginMember.member_name != null}">
		<strong>${loginMember.member_name}</strong> 님 환영합니다.
	</c:if>
	<%-- ${loginMember.member_name == null ? "손님" : loginMember.member_name} 님 환영합니다. --%>
	
	<a href="${contextPath}/auth/logout.do" class="btn btn-warning">로그아웃</a>
</div>
