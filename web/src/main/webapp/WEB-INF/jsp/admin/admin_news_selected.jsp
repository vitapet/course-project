<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Selected News</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<c:set var="attrUrl" value="${url}" scope="request"/>
<a href="${pageContext.request.contextPath}/${attrUrl}/news">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<a href="${pageContext.request.contextPath}/${attrUrl}/news/<c:out value="${newsObj.id}"/>/edit">Update</a><br>
<form action="${pageContext.request.contextPath}/${attrUrl}/news/<c:out value="${newsObj.id}"/>/delete" method="post">
    <button type="submit">Delete</button>
</form>
<jsp:include page="../res/admin_news_with_comments.jsp"/>
</body>
</html>
