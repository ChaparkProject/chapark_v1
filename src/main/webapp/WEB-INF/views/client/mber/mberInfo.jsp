<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/join.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<div class="signup-form">
	<div class="container-lg">
		<h2 class="text-center">회원정보수정</h2>
	</div>
	<div class="mb-3">
		<label for="MBER_ID" class="form-label">아이디</label>
		<input type="text" class="form-control" id="mberId" name="mberId" placeholder="${mberInfo.MBER_ID}" readonly>
	</div>
	<div class="mb-3">
		<label for="MBER_PW" class="form-label">비밀번호</label>
		<a class="btn btn-success" href="#" role="button">비밀번호 변경</a>
	</div>
	<div class="mb-3">
		<label for="MBER_NAME" class="form-label">이름</label>
		<input type="text" class="form-control" id="mberName" name="mberName" placeholder="${mberInfo.MBER_NAME}" readonly>
	</div>
	<div class="mb-3">
		<label for="MBER_EMAIL" class="form-label">이메일</label>
		<input type="email" class="form-control" id="mberEmail" name="mberEmail" placeholder="${mberInfo.MBER_EMAIL}" readonly>
	</div>	
	<div class="mb-3">
		<label for="MBER_ZIP" class="form-label">우편번호</label>
		<input type="text" class="form-control" id="postcode" name="mberZip" required="required" placeholder="${mberInfo.MBER_ZIP}" readonly>
	</div>
	<div class="mb-3">
		<label for="MBER_JIB" class="form-label">지번주소</label>
		<input type="text" class="form-control" id="jibunAddress" name="mberJib" required="required" placeholder="${mberInfo.MBER_JIB}" readonly>
	</div>
	<div class="mb-3">
		<label for="MBER_ADDR" class="form-label">상세주소</label>
		<input type="text" class="form-control" id="mberAddr" name="mberAddr"  placeholder="${mberInfo.MBER_ADDR}" readonly>
	</div>
	<div class="mb-3">
		<label for="MBER_TEL" class="form-label">전화번호</label>
		<input type="text" class="form-control" id="mberTel" name="mberTel" placeholder="${mberInfo.MBER_TEL}" readonly>
	</div>
</div>