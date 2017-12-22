<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>User Cart</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/user">Main</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>

<form action="${pageContext.request.contextPath}/user/cart" method="post">
    <table class="table table-striped">
        <tr>
            <th></th>
            <th>Index</th>
            <th>Goods</th>
            <th>Price</th>
            <th>Count</th>
        </tr>
        <c:choose>
            <c:when test="${empty cart.cart}">
                <tr>
                    <td colspan="8">
                        There are no purchases
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${cart.cart}" var="item" varStatus="loop">
                    <tr>
                        <td><input type="checkbox" name="check" value="${item.id}"></td>
                        <td><c:out value="${loop.index+1}"/></td>
                        <td><c:out value="${item.goods.name}"/></td>
                        <td><c:out value="${item.goods.price}"/></td>
                        <td><input type="text" name="count${item.id}" value="<c:out value="${item.count}"/>"></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <c:if test="${not empty cart.cart}">
        <button type="submit" class="btn btn-default" name="confirm" value="confirm">Confirm</button>
        <button type="submit" class="btn btn-default" name="delete" value="delete">Delete</button>
    </c:if>
</form>
</body>
</html>
