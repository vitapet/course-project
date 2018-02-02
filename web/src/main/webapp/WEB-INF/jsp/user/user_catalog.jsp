<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<p>${curCategory}</p>
<form:form method="post" modelAttribute="searchForm">
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <tr>
            <td><form:input path="name"/></td>
            <td><form:input path="price"/></td>
            <td>
                <button type="submit">Search</button>
            </td>
        </tr>
    </table>
    <c:forEach items="${categories}" var="category">
        <a name="id" href="${pageContext.request.contextPath}/user/catalog/<c:out value="${category}"/>"><c:out
                value="${category}"/></a>
    </c:forEach>
    <c:set var="categoryAll" value="all"/>
    <a name="id" href="${pageContext.request.contextPath}/user/catalog/<c:out value="${categoryAll}"/>">All</a><br>
    <c:choose>
        <c:when test="${empty products}">
            <p>There are no goods at this category.</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${products}" var="product">
                <c:set var="tempProduct" value="${product}" scope="request"/>
                <jsp:include page="../res/product_info.jsp"/>
                <%--<form action="${pageContext.request.contextPath}/user/catalog/cart/add" method="post">--%>
                <%--<input type="hidden" name="productId" value="<c:out value="${product.id}"/>"/>--%>
                <%--<input type="hidden" name="curCategory" value="<c:out value="${curCategory}"/>"/>--%>
                <%--<button type="submit">Add to cart</button>--%>
                <%--</form>--%>
                <a name="id" href="${pageContext.request.contextPath}/user/catalog/cart/add/<c:out value="${curCategory}"/>/<c:out value="${product.id}"/>">Add to cart</a><br>
                <br>
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</form:form>
</body>
</html>
