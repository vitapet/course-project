<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <jsp:include page="res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <jsp:include page="res/user_info_empty.jsp"/>
    <label>Повторите пароль:&nbsp;</label><input type="text" name="passwordCheck" required><br>
    <button type="submit">Save</button>
</form>
</body>
</html>
