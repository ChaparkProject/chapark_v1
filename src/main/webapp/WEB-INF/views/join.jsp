<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Sign Up</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f8f9fa;
}
.signup-form {
	background-color: #ffffff;
	padding: 2rem;
	border-radius: 0.5rem;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
	width: 400px;
}
.signup-form .form-control {
	margin-bottom: 1rem;
}
.signup-form .btn {
	width: 100%;
}
</style>
</head>
<body>
	<div class="signup-form">
		<h2 class="text-center">Sign Up</h2>
		<form id="signupForm" action="<c:url value='/join.do' />" method="post">
			<div class="mb-3">
				<label for="MBER_SEQ" class="form-label">Member Sequence</label>
				<input type="text" class="form-control" id="MBER_SEQ" name="MBER_SEQ" required="required">
			</div>
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
			<div class="mb-3">
				<label for="MBER_AUTH" class="form-label">Auth</label>
				<input type="text" class="form-control" id="MBER_AUTH" name="MBER_AUTH" required="required">
			</div>
			<button type="submit" class="btn btn-primary">Sign Up</button>
		</form>
	</div>
</body>
</html>
