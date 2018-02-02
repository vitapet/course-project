<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<p>Заказ</p>
<form:form method="post" modelAttribute="order">
    <table>
        <tr>
            <th>Id</th>
            <th>Дата</th>
            <th>Статус</th>
        </tr>
        <tr>
            <td><form:input path="id" readonly="true"/></td>
            <td><form:input path="timestamp" readonly="true"/></td>
            <td><form:select path="status">
                <form:options items="${statuses}"/>
            </form:select></td>
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
                <td><form:input path="orderProducts[${itemNum.index}].product.name" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].count" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].product.price" readonly="true"/></td>
                <td><form:input path="orderProducts[${itemNum.index}].amount" readonly="true"/></td>
                <td><form:hidden path="orderProducts[${itemNum.index}].product.id" readonly="true"/></td>
                <td><form:hidden path="orderProducts[${itemNum.index}].id" readonly="true"/></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty order.orderProducts}">
        <button type="submit" class="btn btn-default" name="confirm" value="confirm">Save</button>
    </c:if>
</form:form>
