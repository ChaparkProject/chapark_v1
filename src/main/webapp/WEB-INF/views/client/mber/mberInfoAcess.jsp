<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/join.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<div class="login-form">
	<h2 class="text-center">회원정보수정</h2>
	<form id="loginForm" method="post">
		<div class="mb-3">
			<label for="password" class="form-label">비밀번호</label> 
			<input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
		</div>
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<button type="submit" class="btn btn-primary" onclick ='loginCheck()'>비밀번호 확인</button>
	</form>
</div>
