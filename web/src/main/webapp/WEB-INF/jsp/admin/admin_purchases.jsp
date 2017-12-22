<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Purchases</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/admin">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<p>Заказы</p>
<c:forEach items="${purchases}" var="purchase">
    <label>Товар:&nbsp;</label><c:out value="${purchase.goods.name}"/><br>
    <label>Количество:&nbsp;</label><c:out value="${purchase.count}"/><br>
    <label>Дата:&nbsp;</label><c:out value="${purchase.timestamp}"/><br>
    <label>Статус:&nbsp;</label><c:out value="${purchase.status}"/><br>
    <label>Сумма:&nbsp;</label><c:out value="${purchase.amount}"/><br>
    <a href="${pageContext.request.contextPath}/admin/purchases/update?id=${purchase.id}">Update</a><br>
</c:forEach>
</body>
</html>
