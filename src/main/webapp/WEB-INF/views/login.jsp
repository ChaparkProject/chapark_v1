<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	// 로그인 불가 alert 처리
	//const msg = "<c:out value='${msg}'/>";
	//const url = "<c:out value='${url}'/>";
	//alert(msg);

	//location.href = url;
	function login() {
		
		if($('#mberId').val() == '') {
			alert("아이디를 입력하세요");
			$('#mberId').focus;
			return;
		} else if($('#mberPw').val() == '') {
			alert("비밀번호를 입력하세요");
			$('#mberPw').focus;
			return;
		}
		
		$.ajax({
			type : "POST", 
			url : "/chapark/login.do", 
			data : {
				'mberId' : $('#mberId').val(),
				'mberPw' : $('#mberPw').val() 
			},
			success : function(result) {
				if(result) {
					location.href = "/chapark/mber.do";
				} else {
					alert('아이디와 비밀번호가 일치하지 않습니다.');
					$('#mberId').focus;
				}
			},
			error: function() {
				alert("오류가 발생했습니다."); 
			}
		});
	}
</script>

<form id="loginForm">
	<div>
		<input type="text" id="mberId" placeholder="아이디" /> 
		<input type="password" id="mberPw" placeholder="비밀번호" />
		<a onclick="login()">로그인</a>
		<!--  <button onclick="login()">로그인</button> -->
	</div>
</form>