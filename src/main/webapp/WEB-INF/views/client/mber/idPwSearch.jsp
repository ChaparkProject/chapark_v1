<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

window.onload = function () {
	search_select('1');
}

function search_select(num) {
	if(num == '1') { //아이디 찾기
		document.getElementById("searchId").style.display = "";
		document.getElementById("searchPw").style.display = "none";
	} else { //비밀번호 찾기
		document.getElementById("searchId").style.display = "none";
		document.getElementById("searchPw").style.display = "";
		
	}
}
</script>
<form id="findForm" method="post">
	<div id= "searchId">
		<h3>아이디 찾기</h3>
		<div>
			<label for="mberName">이름</label>
			<input type="text" name="mberName" id="mberName" placeholder="이름">
		</div>
		<div>
			<label for="mberTel">전화번호</label>
			<input type="text" name="mberTel" id="mberTel" placeholder="전화번호">
		</div>
	</div>
	<div id= "searchPw">
		<h3>비밀번호 찾기</h3>
		<div>
			<label for="mberId">아이디</label>
			<input type="text" name="mberId" id="mberId" placeholder="아이디">
		</div>
		<div>
			<label for="mberTel">전화번호</label>
			<input type="text" name="mberTel" id="mberTel" placeholder="전화번호">
		</div>
	</div>
	<div>
		<button type="button" onclick="search_select(1)">아이디 찾기</button>
		<button type="button" onclick="search_select(2)">비밀번호 찾기</button>
	</div>
</form>