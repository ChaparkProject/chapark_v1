<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${pageContext.request.contextPath}/asset/css/join.css" rel="stylesheet" type="text/css">
    
<div class="signup-form">
	<h2 class="text-center">Sign Up</h2>
	<form id="signupForm" action="<c:url value='/join.do' />" method="post">
		<div class="mb-3">
			<label for="MBER_ID" class="form-label">ID</label>
			<input type="text" class="form-control" id="mberId" name="mberId" required="required">
		</div>
		<div class="mb-3">
			<button type="button" class="btn btn-primary " onclick="idCheck();">아이디 중복체크</button>
		</div>
		<div><span id="result_checkId" style="font-size:12px;"></span></div>
		<div class="mb-3">
			<label for="MBER_PW" class="form-label">Password</label>
			<input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_NAME" class="form-label">Name</label>
			<input type="text" class="form-control" id="mberName" name="mberName" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_EMAIL" class="form-label">Email</label>
			<input type="email" class="form-control" id="mberEmail" name="mberEmail" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_ZIP" class="form-label">Zip Code</label>
			<input type="text" class="form-control" id="mberZip" name="mberZip" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_JIB" class="form-label">Address (JIB)</label>
			<input type="text" class="form-control" id="mberJib" name="mberJib" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_ADDR" class="form-label">Address</label>
			<input type="text" class="form-control" id="mberAddr" name="mberAddr" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_TEL" class="form-label">Telephone</label>
			<input type="text" class="form-control" id="mberTel" name="mberTel" required="required">
		</div>
		<button type="submit" class="btn btn-primary">Sign Up</button>
	</form>
</div>

<script>
// 아이디 중복체크 미클릭시
let submitCheck = false;

function joinCheck() {
	if(submitCheck) {
		return true;
	} else {
		alert('아이디 중복체크를 해야합니다');
		return false;
	}
}
// 아이디 중복체크
function idCheck() {
	const mberId = $('#mberId').val();
	
	$.ajax({
		type : "POST",
		url  : "/idCheck.do",
		data : {mberId : mberId},
		success  : function(data) {
			if(data.result == 'success') {
				//$("#result_checkId").html("아이디를 사욯하실수 있습니다.").css("color","green");
				alert(data.msg);
			} else{
				//$("#result_checkId").html("이미 사용중인 아이디 입니다").css("color","red");
				alert(data.msg);
			}
		}
		,error : function(data) {
			alert(data.msg);
		}
	});
}
</script>