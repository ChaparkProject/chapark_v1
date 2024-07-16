<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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

