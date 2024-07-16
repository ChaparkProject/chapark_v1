<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="IsErrorPage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="/assets/css/common.css" rel="stylesheet" type="text/css">
<script>
function pageChange(page) {
	$.ajax({
		type: "POST",
		url: "/changePage",
		data: {
			'page': page,
		},
		dataType: 'html',
		success: function(result) {
            $('#mainContent').html(result);
		},
		error: function() {
			alert("오류가 발생했습니다.");
		}
	});
}

function login() {
	if ($('#mberId').val() == '') {
		alert("아이디를 입력하세요");
		$('#mberId').focus();
		return;
	} else if ($('#mberPw').val() == '') {
		alert("비밀번호를 입력하세요");
		$('#mberPw').focus();
		return;
	}
	
	$.ajax({
		type: "POST",
		url: "/chapark/login.do",
		data: {
			'mberId': $('#mberId').val(),
			'mberPw': $('#mberPw').val()
		},
		success: function(result) {
			if (result) {
				location.href = "/chapark/mber.do";
			} else {
				alert('아이디와 비밀번호가 일치하지 않습니다.');
				$('#mberId').focus();
			}
		},
		error: function() {
			alert("오류가 발생했습니다.");
		}
	});
}
</script>
<html> 
	<head>
		<style type="text/css">
		</style>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	</head>
	<body class="pt-5">
		<jsp:include page="header.jsp" />
		
		<div class="container-fluid" >
			<div class="row d-flex">
				<div id="mainContent"></div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />	
	</body>
</html>


