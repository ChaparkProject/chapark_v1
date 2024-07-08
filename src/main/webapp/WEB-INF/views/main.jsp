<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h2>Welcome</h2>
	<sec:authorize access="isAuthenticated()">
		<p>
			Logged in as:
			<sec:authentication property="principal.username" />
		</p>
		<form action="<c:url value='/logout' />" method="post">
			<input type="submit" value="Logout" />
		</form>
	</sec:authorize>
</body>
</html>
