<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
    <jsp:include page="../res/cart.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/user">Return</a><br>

<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<p>${categoryName}</p>

<c:forEach items="${categories}" var="category">
    <a name="id" href="${pageContext.request.contextPath}/user/catalog?name=${category.category}"><c:out
            value="${category.category}"/></a>
</c:forEach>
<a name="id" href="${pageContext.request.contextPath}/user/catalog?name=all">All</a><br>
<c:choose>
    <c:when test="${empty listGoods}">
        <p>There are no goods at this category.</p>
    </c:when>
    <c:otherwise>
        <c:forEach items="${listGoods}" var="goods">
            <c:set var="tempGoods" value="${goods}" scope="request"/>
            <%--<input type="radio" name="id" value="<c:out value="${goods.id}"/>">--%>
            <jsp:include page="../res/goods_info.jsp"/>
            <form action="${pageContext.request.contextPath}/user/catalog" method="post">
                <input type="hidden" name="id" value="${goods.id}">
                <button type="submit">Add to cart</button>
            </form>
            <br>
            <br>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
