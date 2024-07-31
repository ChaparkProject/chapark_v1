<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/client/css/join.css" rel="stylesheet" type="text/css">
<div class="signup-form">
	<h2 class="text-center">회원가입</h2>
	<form id="signupForm" action="<c:url value='/join.do' />" method="post">
		<div class="mb-3">
			<label for="MBER_ID" class="form-label">ID</label>
			<input type="text" class="form-control" id="mberId" name="mberId" required="required">
		</div>
		<div><span id="result_checkId" style="font-size:12px;"></span></div>
		<div class="mb-3">
			<button type="button" class="btn btn-primary " onclick="idCheck();">아이디 중복체크</button>
		</div>
		<div class="mb-3">
			<label for="MBER_PW" class="form-label">Password</label>
			<input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_NAME" class="form-label">이름</label>
			<input type="text" class="form-control" id="mberName" name="mberName" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_EMAIL" class="form-label">Email</label>
			<input type="email" class="form-control" id="mberEmail" name="mberEmail" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_ZIP" class="form-label">우편번호</label>
			<input type="text" class="form-control" id="postcode" name="mberZip" required="required" readonly="readonly" onclick="daumPostcode()">
		</div>
		<div class="mb-3">
			<label for="MBER_JIB" class="form-label">지번주소</label>
			<input type="text" class="form-control" id="jibunAddress" name="mberJib" required="required" readonly="readonly" onclick="daumPostcode()">
		</div>
		<div class="mb-3">
			<label for="MBER_ADDR" class="form-label">상세주소</label>
			<input type="text" class="form-control" id="mberAddr" name="mberAddr" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_TEL" class="form-label">전화번호</label>
			<input type="text" class="form-control" id="mberTel" name="mberTel" required="required">
		</div>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
	<span id="guide" class="fs14 pc_red addrsRoad"></span>
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
				$("#result_checkId").html("아이디를 사욯하실수 있습니다.").css("color","green");
			} else{
				$("#result_checkId").html("이미 사용중인 아이디 입니다").css("color","red").att("tabindex", -1).focus();
			}
		}
		,error : function(data) {
			if(data.result == 'fail') {
				alert("오류가 발생했습니다");
			}
		}
	});
}

//카카오 주소 api url에 파라미터 안붙게 하기 위함
var script = document.createElement('script');
script.src = "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
document.head.appendChild(script);

//카카오 주소 api
function daumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if(data.buildingName !== '' && data.apartment === 'Y'){
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if(extraRoadAddr !== ''){
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('postcode').value = data.zonecode; //우편번호
			document.getElementById("jibunAddress").value = data.jibunAddress; //지번 주소
		}
	}).open();
}
</script>