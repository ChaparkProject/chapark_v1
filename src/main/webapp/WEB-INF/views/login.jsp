<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	// 로그인 불가 alert 처리
	//const msg = "<c:out value='${msg}'/>";
	//const url = "<c:out value='${url}'/>";
	//alert(msg);

	//location.href = url;
	function loginButton() {
		var mberId = document.getElementById("mberId").value;
		var mberPw = document.getElementById("mberPw").value;

		$.ajax({
			type : "POST", 
			url : "/loginCheck.do", 
			data : {
				mberId: mberId,
				mberPw: mberPw
			},
			success : function(result) {
				alert('로그인 성공!');
				location.href = "/sucessPage";
			},
			error : function(result) {
				alert('로그인 실패');
			}
		});
	}
</script>

<form id="loginForm">
	<div>
		<input type="text" id="mberId" placeholder="아이디" /> 
		<input type="password" id="mberPw" placeholder="비밀번호" />
		<button onclick="loginButton()">로그인</button>
	</div>
</form>