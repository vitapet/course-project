<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<p>Заказы</p>
<form:form method="post" modelAttribute="orders">

    <c:forEach items="${orders}" var="order" varStatus="number">
        <table>
            <tr>
                <th>Id</th>
                <th>Дата</th>
                <th>Статус</th>
            </tr>
            <tr>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.timestamp}"/></td>
                <td><c:out value="${order.status}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/${attrUrl}/orders/<c:out value="${order.id}"/>/edit">Edit</a><br>
                </td>
            </tr>
        </table>
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
                    <td><c:out value="${item.product.name}"/></td>
                    <td><c:out value="${item.count}"/></td>
                    <td><c:out value="${item.product.price}"/></td>
                    <td><c:out value="${item.amount}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </c:forEach>
</form:form>
