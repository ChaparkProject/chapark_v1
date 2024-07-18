<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
				<label for="username" class="form-label">Username</label> 
					<input type="text" class="form-control" id="username" name="username" required="required">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> 
					<input type="password" class="form-control" id="password" name="password" required="required">
			</div>
			<button type="submit" class="btn btn-primary">Sign Up</button>
		</form>
	</div>
</body>
</html>

