<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>

// 로그인 불가 alert 처리
const msg = "<c:out value='${msg}'/>";
const url = "<c:out value='${url}'/>";
alert(msg);
location.href = url;
</script>