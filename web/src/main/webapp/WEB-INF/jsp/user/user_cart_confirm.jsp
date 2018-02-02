<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form method="post" action="${pageContext.request.contextPath}/user/cart/confirm" modelAttribute="order">
    <table>
        <tr>Order items</tr>
        <tr>
            <th>Product</th>
            <th>Count</th>
            <th>Price</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${order.orderProducts}" var="item" varStatus="itemNum">
            <tr>
                <td><form:input path="orderProducts[${itemNum.index}].product.name" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].count" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].product.price" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].amount" readonly="true"/></td>
                <td><form:hidden path="orderProducts[${itemNum.index}].product.id" readonly="true"/></td>
            </tr>
        </c:forEach>
    </table>
    <label>Total amount:&nbsp;</label><c:out value="${totalAmount}"/><br>
    <button type="submit" class="btn btn-default" name="confirm" value="confirm">Confirm</button>
</form:form>
</body>
</html>
