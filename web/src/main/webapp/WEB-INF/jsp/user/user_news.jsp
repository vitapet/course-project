<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>News</title>

</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
    <jsp:include page="../res/cart.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/user">Return</a><br>
<jsp:include page="../res/news_with_comments.jsp"/>
<br>
<form action="${pageContext.request.contextPath}/user/news" method="post">
    <input type="hidden" name="newsId" value="${news.id}">
    <label>Комментарий:&nbsp;</label><br>
    <input type="text" name="text" required><br>
    <button type="submit">Оставить комментарий</button>
</form>
</body>
</html>
