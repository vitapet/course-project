<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Update Purchase</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
    <jsp:include page="../res/cart.jsp"/>
</header>
<p>Заказ</p>
<form action="${pageContext.request.contextPath}/user/info/purchases/update" method="post">
    <input type="hidden" name="id" value="${purchase.id}">
    <label>Товар:&nbsp;</label><input type="text" name="goods" value="<c:out value="${purchase.goods.name}"/>"
                                      disabled><br>
    <input type="hidden" name="goods_id" value="${purchase.goods.id}">
    <label>Количество:&nbsp;</label><input type="text" name="count" value="<c:out value="${purchase.count}"/>"
                                           required><br>
    <label>Дата:&nbsp;</label><input type="text" name="date" value="<c:out value="${purchase.timestamp}"/>"
                                     disabled><br>
    <label>Статус:&nbsp;</label><input type="text" name="status" value="<c:out value="${purchase.status}"/>"
                                       disabled><br>
    <button type="submit" value="save" name="save">Save</button>
    <button type="submit" value="delete" name="delete">Delete</button>
    <a href="${pageContext.request.contextPath}/user/info/purchases">Return</a><br>
</form>
</body>
</html>
