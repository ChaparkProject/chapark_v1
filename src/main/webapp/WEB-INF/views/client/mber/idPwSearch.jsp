<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

document.addEventListener('DOMContentLoaded', function () {
    search_select('1');
});


function search_select(num) {
	if(num == '1') { //아이디 찾기
		document.getElementById("searchId").style.display = "block";
		document.getElementById("searchPw").style.display = "none";
		document.getElementById("id").style.display = "none";
		document.getElementById("pw").style.display = "block";
	} else { //비밀번호 찾기
		document.getElementById("searchId").style.display = "none";
		document.getElementById("searchPw").style.display = "block";
		document.getElementById("id").style.display = "block";
		document.getElementById("pw").style.display = "none";
		
	}
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
	<div id= "searchPw" class="mb-3" style="display:none;">
		<h3 class="text-center">비밀번호 찾기</h3>
		<div>
			<label for="mberId" class="form-label">아이디</label>
			<input type="text" name="mberId" id="mberId" class="form-control" placeholder="아이디">
		</div>
		<div>
			<label for="mberTel" class="form-label">전화번호</label>
			<input type="text" name="mberTel" id="mberTel" class="form-control" placeholder="전화번호">
		</div>
	</div>
	<div id="id" style="display:none;">
		<button type="button" class="btn btn-primary" onclick="search_select(1)">아이디 찾기</button>
	</div>
	<div id="pw" >
		<button type="button" class="btn btn-primary" onclick="search_select(2)">비밀번호 찾기</button>
	</div>
</form>