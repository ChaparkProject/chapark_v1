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
			<input type="text" value="" />
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
			<tr>
			<td>${member.mberId}</td>
            <td>${member.mberPw}</td>
            <td>${member.mberName}</td>
            <td>${member.mberEmail}</td>
            <td>${member.mberZip}</td>
            <td>${member.mberJib}</td>
            <td>${member.mberAddr}</td>
            <td>${member.mberTel}</td>
            <td>${member.mberAuth}</td>
			</tr>
		</tbody>
		<form action="mber.do" method="GET">
        	<label for="mberId">Member ID:</label>
        	<input type="text" id="mberId" name="mberId" required>
        	<button type="submit">Submit</button>
    </form>
	</table>
</body>
</html>