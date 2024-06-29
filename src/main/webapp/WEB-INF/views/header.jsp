<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script></script>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			ul{
				list-style: none;
			}
			li{
				display: inline-block;
			}
			.btnGroup{
				display: flex;
				flex-direction: row-reverse;
			}
			#header{
				display: flex;
				justify-content: center;
			}
		</style>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="btnGroup">
					<ul>
						<li>회원가입</li>
						<li>로그인</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<h1 id="header">HEADER</h1>
			</div>
		</div>
	</body>
</html>