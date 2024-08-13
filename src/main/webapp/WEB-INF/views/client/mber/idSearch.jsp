<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function searchId(){
	if(nullCheck()) {
		$.ajax({
			url : "/searchId.do",
			type : "POST",
			data : {
				mberName: $('#mberName').val(),
				mberTel: $('#mberTel').val()
			},
			success : function(data) {
				if(data.result == 'true') {
					$('#resultMessage').text("찾으시는 아이디는 '" + data.mberId + "' 입니다.");
				} else {
					$('#resultMessage').text("일치하는 정보가 없습니다.");
				}
				$('#searchId').hide(); // 검색 폼을 숨김
				$('#id').hide(); // 버튼을 숨김
				$('#resultSection').show(); // 결과 섹션을 보여줌
			},
			error : function(){
				alert('오류가 발생했습니다.')
			}
		});
	}
}
function nullCheck() {
	if ($('#mberName').val() == '') {
		alert("사용자 이름을 입력하세요");
		$('#mberName').focus();
		return;
	} else if ($('#mberTel').val() == '') {
		alert("전화번호를 입력하세요");
		$('#mberTel').focus();
		return;
	}
	return true;
}
</script>
<form id="findForm" class="login-form" method="post">
	<div id= "searchId" class="mb-3">
		<h3 class="text-center" >아이디 찾기</h3>
		<div>
			<label for="mberName" class="form-label">이름</label>
			<input type="text" name="mberName" id="mberName" class="form-control" placeholder="이름">
		</div>
		<div>
			<label for="mberTel" class="form-label">전화번호</label>
			<input type="text" name="mberTel" id="mberTel" class="form-control" placeholder="전화번호">
		</div>
	</div>
	<div id="id">
		<button type="button" class="btn btn-primary" onclick="searchId()">아이디 찾기</button>
	</div>
	<!-- 결과를 표시할 부분 -->
	<div id="resultSection" style="display: none;">
		<label id="resultMessage"></label> <br>
		<div class="d-grid gap-2">
			<button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='/login.do'">로그인</button> 
			<button type="button" class="btn btn-secondary btn-sm" onclick="window.location.href='/searchPwPage.do'">비밀번호 찾기</button>
		</div> 
	</div>
</form>