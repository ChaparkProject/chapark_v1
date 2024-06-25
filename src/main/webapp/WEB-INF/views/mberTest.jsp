<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Member List</h2>

	<table border="1">
		<thead>
			<tr>
				<th>Member ID</th>
           		<th>Password</th>
            	<th>Name</th>
            	<th>Email</th>
            	<th>Zip</th>
            	<th>Jib</th>
            	<th>Address</th>
            	<th>Phone</th>
            	<th>Authority</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${memberList}" var="memberList">
				<tr>
					<td>${memberList.mberId}</td>
            		<td>${memberList.mberPw}</td>
            		<td>${memberList.mberName}</td>
            		<td>${memberList.mberEmail}</td>
            		<td>${memberList.mberZip}</td>
            		<td>${memberList.mberJib}</td>
            		<td>${memberList.mberAddr}</td>
            		<td>${memberList.mberTel}</td>
            		<td>${memberList.mberAuth}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>