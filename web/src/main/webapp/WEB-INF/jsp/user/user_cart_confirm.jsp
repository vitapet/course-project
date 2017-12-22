<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Confirm Purchase</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/user/cart">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<form action="${pageContext.request.contextPath}/user/cart/confirm" method="post">
    <table class="table table-striped">
        <tr>
            <th>Index</th>
            <th>Goods</th>
            <th>Price</th>
            <th>Count</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${cart.cart}" var="item" varStatus="loop">
            <tr>
                <td><c:out value="${loop.index+1}"/></td>
                <td><c:out value="${item.goods.name}"/></td>
                <td><c:out value="${item.goods.price}"/></td>
                <td><c:out value="${item.count}"/></td>
                <td><c:out value="${item.amount}"/></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <label>Всего:&nbsp;</label><c:out value="${cart.totalCost}"/>
    <br>
    <c:if test="${not empty cart.cart}">
        <button type="submit" class="btn btn-default" name="confirm" value="confirm">Confirm</button>
    </c:if>
</form>
</body>
</html>
