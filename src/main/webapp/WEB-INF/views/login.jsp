<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login</title>
<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f8f9fa;
}

.login-form {
	background-color: #ffffff;
	padding: 2rem;
	border-radius: 0.5rem;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
}

.login-form .form-control {
	margin-bottom: 1rem;
}

.login-form .btn {
	width: 100%;
}
</style>
</head>
<body>
	<div class="login-form">
		<h2 class="text-center">Login</h2>
		<form id="loginForm" action="<c:url value='/login.do' />" method="post">
			<div class="mb-3">
				<label for="username" class="form-label">ID</label> 
					<input type="text" class="form-control" id="mberId" name="mberId" required="required">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> 
				<input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
			</div>
			<c:if test="${not empty loginError}">
				<div class="alert alert-danger" role="alert">${loginError}</div>
			</c:if>
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
	</div>
</body>
</html>
