<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/asset/css/client/login.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function searchId(){
	$("#findForm").attr("action", "/searchId.do").submit();
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
		<button type="button" class="btn btn-primary" onclick="">아이디 찾기</button>
	</div>
</form>