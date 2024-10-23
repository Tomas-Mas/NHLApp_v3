<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NHL</title>
</head>
<body>

<h1>NHL v3</h1>

<p>Welcome home!</p>

<p> It has been a while, ${user} </p>
<p> you have clicked here ${clicks} times </p>

<a href="/v3/nhl/admin/">Admin</a>
<br>
<a href="/v3/nhl/auth/login">Login</a>
<br>
<a href="/v3/nhl/auth/register">Register</a>

<br><br>

<form id="logoutForm" action="/v3/nhl/auth/logout" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Logout</button>
</form>

</body>
</html>