<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function searchPw(){
	if(nullCheck()) {
		$.ajax({
			url : "/searchPw.do",
			type : "POST",
			data : {
				mberId: $('#mberId').val(),
				mberEmail: $('#mberEmail').val()
			},
			success : function(data) {
				if(data.result == 'true') {
					$('#resultMessage').text("이메일로 임시 비밀번호 발급하였습니다.");
				} else {
					$('#resultMessage').text("일치하는 정보가 없습니다.");
				}
				$('#searchPw').hide(); // 검색 폼을 숨김
				$('#pw').hide(); // 버튼을 숨김
				$('#resultSection').show(); // 결과 섹션을 보여줌
			},
			error : function(){
				alert('오류가 발생했습니다.')
			}
		});
	}
}
function nullCheck() {
	if ($('#mberId').val() == '') {
		alert("아이디를 입력하세요.");
		$('#mberId').focus();
		return;
	} else if ($('#mberEmail').val() == '') {
		alert("이메일을 입력하세요.");
		$('#mberEmail').focus();
		return;
	}
	return true;
}
</script>
<form id="findForm" class="login-form" method="post">
	<div id= "searchPw" class="mb-3">
		<h3 class="text-center">비밀번호 찾기</h3>
		<div>
			<label for="mberId" class="form-label">아이디</label>
			<input type="text" name="mberId" id="mberId" class="form-control" placeholder="아이디">
		</div>
		<div>
			<label for="mberEmail" class="form-label">이메일</label>
			<input type="email" name="mberEmail" id="mberEmail" class="form-control" placeholder="이메일">
		</div>
	</div>
	<div id="pw" >
		<button type="button" class="btn btn-primary" onclick="searchPw()">비밀번호 찾기</button>
	</div>
		<!-- 결과를 표시할 부분 -->
	<div id="resultSection" style="display: none;">
		<label id="resultMessage"></label> <br>
		<div class="d-grid gap-2">
			<button type="button" class="btn btn-primary" onclick="window.location.href='/login.do'">로그인</button>
			<button type="button" class="btn btn-secondary btn-sm" onclick="window.location.href='/searchPwPage.do'">정보변경하기</button>
		</div>
	</div>
</form>