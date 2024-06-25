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
					<td>${memberList.MBER_ID}</td>
            		<td>${memberList.MBER_PW}</td>
            		<td>${memberList.MBER_NAME}</td>
            		<td>${memberList.MBER_EMAIL}</td>
            		<td>${memberList.MBER_ZIP}</td>
            		<td>${memberList.MBER_JIB}</td>
            		<td>${memberList.MBER_ADDR}</td>
            		<td>${memberList.MBER_TEL}</td>
            		<td>${memberList.MBER_AUTH}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>