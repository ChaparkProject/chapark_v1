<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	<div id="pw" >
		<button type="button" class="btn btn-primary" onclick="">비밀번호 찾기</button>
	</div>