<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>NHL</title>
</head>

<body>
	<h1>Register page</h1>
	
	<form:form modelAttribute="userRegistrationForm" method="POST" action="/v3/auth/register-submit">
		<table>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username"/></td>
				<td><form:errors path="username"/></td>
			</tr>
			<tr>
				<td><form:label path="firstname">First name</form:label></td>
				<td><form:input path="firstname"/></td>
				<td><form:errors path="firstname"/></td>
			</tr>
			<tr>
				<td><form:label path="lastname">Last name</form:label></td>
				<td><form:input path="lastname"/></td>
				<td><form:errors path="lastname"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password"/></td>
				<td><form:errors path="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register"/></td>
			</tr>
		</table>
	</form:form>
	
	<a href="/v3/auth/login">Login</a>
	<br>
	<a href="/v3/nhl/home">Home</a>

</body>
</html>