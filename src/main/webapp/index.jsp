<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>

    <title>JSP - Hello World</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">

    <meta name="google-signin-client_id"
          content="314134351747-96fqghf4l57catc97as2q2ibv14l7u0u.apps.googleusercontent.com">
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<h2>Servlet OAuth example</h2>
<br>
<a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/showAll&response_type=code&client_id=314134351747-96fqghf4l57catc97as2q2ibv14l7u0u.apps.googleusercontent.com&approval_prompt=force">Login google</a>
</body>
</html>
