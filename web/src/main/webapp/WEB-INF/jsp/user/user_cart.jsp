<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<form:form method="post" modelAttribute="order">
    <table>
        <tr>Order items</tr>
        <tr>
            <th></th>
            <th>Product</th>
            <th>Count</th>
            <th>Price</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${order.orderProducts}" var="item" varStatus="itemNum">
            <tr>
                <td><input type="checkbox" name="check" value="${item.id}"></td>
                <td><form:input path="orderProducts[${itemNum.index}].product.name" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].count" type =""/></td>
                <td><form:input path="orderProducts[${itemNum.index}].product.price" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].amount" readonly="true"/></td>
                <td><form:hidden path="orderProducts[${itemNum.index}].product.id" readonly="true"/></td>
                <td><form:hidden path="orderProducts[${itemNum.index}].id" readonly="true"/></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty order.orderProducts}">
        <button type="submit" class="btn btn-default" name="confirm" value="confirm">Confirm</button>
        <button type="submit" class="btn btn-default" name="delete" value="delete">Delete</button>
    </c:if>
</form:form>
</body>
</html>
