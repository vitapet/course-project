<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>News</title>
</head>
<body>
<header>
    <jsp:include page="res/menu.jsp"/>
    <a type="button" href="${pageContext.request.contextPath}/registration">Registration</a>
</header>
<a href="${pageContext.request.contextPath}/">Return</a><br>
<jsp:include page="res/news_with_comments.jsp"/>
</body>
</html>
