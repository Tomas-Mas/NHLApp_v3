<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nhl v3</title>
</head>
<body>
	<h1>Login page</h1>
	
	<p> Hello Mr. ${user} </p>
	
	<form:form modelAttribute="loginForm" method="POST" action="/v3/auth/login-submit">
		<table>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username"/></td>
				<td><form:errors path="username"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password"/></td>
				<td><form:errors path="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"/></td>
			</tr>
		</table>
	</form:form>
	
	<a href="/v3/nhl/home">Home</a>
	<br>
	<a href="/v3/auth/register">Register</a>

</body>
</html>