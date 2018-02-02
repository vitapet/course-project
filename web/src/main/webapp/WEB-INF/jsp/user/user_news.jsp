<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<%--@elvariable id="newComment" type="com.gmail.vitaliapetsenak.shop.service.model.CommentDTO"--%>
<form:form method="post" modelAttribute="newComment">
    <%--@elvariable id="news" type="com.gmail.vitaliapetsenak.shop.service.model.NewsDTO"--%>
    <c:set var="id" value="${news.id}"/>
    <input type="hidden" name="newsId" value="${news.id}">
    <label>Комментарий:&nbsp;</label><br>
    <form:textarea path="text" type="text" id="text"/><br>
    <button type="submit">Оставить комментарий</button>
    <form:errors path="text"/>
</form:form>
</body>
</html>
