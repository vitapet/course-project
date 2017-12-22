<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Admin Catalog</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/root">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<a href="${pageContext.request.contextPath}/root/catalog/add">Add</a><br>
<br>
<p>${categoryName}</p>

<c:forEach items="${categories}" var="category">
    <a name="id" href="${pageContext.request.contextPath}/root/catalog?name=${category.category}"><c:out
            value="${category.category}"/></a>
</c:forEach>
<a name="id" href="${pageContext.request.contextPath}/root/catalog?name=all">All</a><br>
<form action="${pageContext.request.contextPath}/root/catalog" method="post">
    <c:choose>
        <c:when test="${empty listGoods}">
            <p>There are no goods at this category.</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${listGoods}" var="goods">
                <c:set var="tempGoods" value="${goods}" scope="request"/>
                <input type="radio" name="id" value="<c:out value="${goods.id}"/>">
                <jsp:include page="../res/goods_info.jsp"/>
                <c:out value="${goods.isDeleted}"/>
                <a href="${pageContext.request.contextPath}/root/catalog/update?id=${goods.id}">Update</a><br>
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <c:if test="${not empty listGoods}">
        <button type="submit">Delete</button>
    </c:if>
</form>
</body>
</html>
