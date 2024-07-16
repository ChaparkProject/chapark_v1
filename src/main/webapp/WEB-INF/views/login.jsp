<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<<<<<<< HEAD
=======
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
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="mberId" name="mberId" required="required">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="mberPw" name="mberPw" required="required">
        </div>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                아이디와 비밀번호가 일치하지 않습니다.
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
<script>
    function login() {
        if ($('#mberId').val() === '') {
            alert("아이디를 입력하세요");
            $('#mberId').focus();
            return;
        } else if ($('#mberPw').val() === '') {
            alert("비밀번호를 입력하세요");
            $('#mberPw').focus();
            return;
        }
    }

    $('#loginForm').on('submit', function() {
        login();
    });
</script>
>>>>>>> branch 'master' of https://github.com/ChaparkProject/chapark_v1.git

<<<<<<< HEAD
<div class="col mx-auto" style="width: 50%">
	<div class="jumbotron" style="padding-top: 20px">
		<form id="loginForm">
			<div class="form-group">
				<input 
					class="form-control" 
					type="text" 
					name="mberId" 
					id="mberId" 
					placeholder="아이디"
				/>
			</div>
			<div class="form-group">
				<input 
					class="form-control" 
					type="password" 
					name="mberPw" 
					id="mberPw" 
					placeholder="비밀번호"
				/>
			</div>
			<button onclick="login()">로그인</button>
		</form>
	</div>
</div>

=======
</body>
</html>
>>>>>>> branch 'master' of https://github.com/ChaparkProject/chapark_v1.git
