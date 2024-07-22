<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${pageContext.request.contextPath}/asset/css/join.css" rel="stylesheet" type="text/css">
    
<div class="signup-form">
	<h2 class="text-center">Sign Up</h2>
	<form id="signupForm" action="<c:url value='/join.do' />" method="post">
		<div class="mb-3">
			<label for="MBER_ID" class="form-label">ID</label>
			<input type="text" class="form-control" id="MBER_ID" name="MBER_ID" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_PW" class="form-label">Password</label>
			<input type="password" class="form-control" id="MBER_PW" name="MBER_PW" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_NAME" class="form-label">Name</label>
			<input type="text" class="form-control" id="MBER_NAME" name="MBER_NAME" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_EMAIL" class="form-label">Email</label>
			<input type="email" class="form-control" id="MBER_EMAIL" name="MBER_EMAIL" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_ZIP" class="form-label">Zip Code</label>
			<input type="text" class="form-control" id="MBER_ZIP" name="MBER_ZIP" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_JIB" class="form-label">Address (JIB)</label>
			<input type="text" class="form-control" id="MBER_JIB" name="MBER_JIB" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_ADDR" class="form-label">Address</label>
			<input type="text" class="form-control" id="MBER_ADDR" name="MBER_ADDR" required="required">
		</div>
		<div class="mb-3">
			<label for="MBER_TEL" class="form-label">Telephone</label>
			<input type="text" class="form-control" id="MBER_TEL" name="MBER_TEL" required="required">
		</div>
		<button type="submit" class="btn btn-primary">Sign Up</button>
	</form>
</div>