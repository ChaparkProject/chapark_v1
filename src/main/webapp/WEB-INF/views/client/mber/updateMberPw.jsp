<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/join.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<div class="login-form">
	<h2 class="text-center">비밀번호 변경</h2>
	<form id="loginForm" method="post">
		<div class="mb-3">
			<label for="password" class="form-label">현재 비밀번호</label> 
			<input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
			<div class="alert alert-danger" id="mberPwAlert" role="alert" style="display: none;"></div>
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">새 비밀번호</label> 
			<input type="password" class="form-control" id="newMberPw" name="newMberPw" required="required">
			<div class="alert alert-danger" id="newMberPwAlert" role="alert" style="display: none;"></div>
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">새 비밀번호 확인</label> 
			<input type="password" class="form-control" id="checkNewPw" name="checkNewPw" required="required">
			<div class="alert alert-danger" id="checkNewPwAlert" role="alert" style="display: none;"></div>
		</div>
		<button type="submit" class="btn btn-primary" onclick="changePw(event)">비밀번호 변경</button>
	</form>
</div>
<script>
function changePw(event) {
	
	event.preventDefault(); // 폼의 기본 제출 동작을 방지
	
	$("#mberPwAlert, #newMberPwAlert, #checkNewPwAlert").hide().html(""); // 경고 메시지 초기화 및 숨기기
	
	$.ajax({
		type : "POST",
		url : "/updatePassword.do",
		data : {
			mberPw : $("#mberPw").val(),
			newMberPw : $("#newMberPw").val(),
			checkNewPw : $("#checkNewPw").val(),
		},
		success : function(data) {
			if(data.result === 'update') {
				window.location.href = '/mberInfo.do';
			} else if (data.result === 'C') {
				$("#mberPwAlert").html("비밀번호가 다릅니다.").show();
			} else if (data.result === 'B'){
				$("#newMberPwAlert").html("기존 비밀번호와 동일합니다.").show();
			} else if (data.result === 'A'){
				$("#checkNewPwAlert").html("새 비밀번호와 다릅니다.").show();
			}
		},
		error : function() {
			alert('오류가 발생했습니다.');
		}
	});
}

</script>

