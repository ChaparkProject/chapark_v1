<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function login() {
		if ($('#mberId').val() == '') {
			alert("아이디를 입력하세요");
			$('#mberId').focus();
			return;
		} else if ($('#mberPw').val() == '') {
			alert("비밀번호를 입력하세요");
			$('#mberPw').focus();
			return;
		}
		
		$.ajax({
			type: "POST",
			url: "/chapark/login.do",
			data: {
				'mberId': $('#mberId').val(),
				'mberPw': $('#mberPw').val()
			},
			success: function(result) {
				if (result) {
					location.href = "/chapark/mber.do";
				} else {
					alert('아이디와 비밀번호가 일치하지 않습니다.');
					$('#mberId').focus();
				}
			},
			error: function() {
				alert("오류가 발생했습니다.");
			}
		});
	}
</script>

<form id="loginForm" action="<c:url value='/login.do' />" method="post">
	<label for="username">Username:</label>
		<input type="text" id="mberId" name="mberId" required="required" />
		<br/>
		<label for="password">Password:</label>
		<input type="password" id="mberId" name="mberId" required="required" />
		<br/>
		<input type="submit" value="Login" />
		<c:if test="${param.error != null}">
			<p style="color:red;">error!</p>
		</c:if>
</form>
