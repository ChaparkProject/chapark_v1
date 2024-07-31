<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">

<script>
	// 아이디 비번 빈값 체크
	function loginCheck() {
		if ($('#mberId').val() == '') {
			alert("아이디를 입력하세요");
			$('#mberId').focus();
			return;
		} else if ($('#mberPw').val() == '') {
			alert("비밀번호를 입력하세요");
			$('#mberPw').focus();
			return;
		}
	}
</script>

<div class="login-form">
	<h2 class="text-center">Login</h2>
	<form id="loginForm" action="<c:url value='/login.do' />" method="post">
		<div class="mb-3">
			<label for="username" class="form-label">ID</label> 
				<input type="text" class="form-control" id="mberId" name="mberId" required="required">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password</label> 
			<input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
		</div>
		<c:if test="${not empty loginError}">
			<div class="alert alert-danger" role="alert">${loginError}</div>
		</c:if>
		<button type="submit" class="btn btn-primary" onclick ='loginCheck()'>Login</button>
		<div>
			<a href = "#" type="button" onclick="pageChange('idPwSearch.jsp')" >아이디 찾기/비밀번호 찾기</a>
		</div>
	</form>
</div>
