<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Return</a><br>
<p><c:out value="${error}"/></p>
<c:remove var="error" scope="session"/>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="login">Login</label>
    <input type="text" id="login" value="<c:out value="${loginObject.login}"/>" name="login" placeholder="Login"><br>
    <label for="password">Password</label>
    <input type="password" id="password" value="<c:out value="${loginObject.password}"/>" name="password"
           placeholder="Password"><br>
    <button type="submit">Login</button>
</form>
</body>
</html>
