<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Update Purchase</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/root/purchases">Return</a><br>
<p>Заказ</p>
<form action="${pageContext.request.contextPath}/root/purchases/update" method="post">
    <input type="hidden" name="id" value="${purchase.id}">
    <label>Товар:&nbsp;</label><input type="text" name="goods" value="<c:out value="${purchase.goods.name}"/>"
                                      disabled><br>
    <input type="hidden" name="goods_id" value="${purchase.goods.id}">
    <label>Количество:&nbsp;</label><input type="text" name="count" value="<c:out value="${purchase.count}"/>"
                                           disabled><br>
    <label>Дата:&nbsp;</label><input type="text" name="date" value="<c:out value="${purchase.timestamp}"/>"
                                     disabled><br>
    <label>Статус:&nbsp;</label>
    <select name="status" required>
        <c:forEach items="${statusList}" var="status">
            <c:choose>
                <c:when test="${status eq purchase.status}">
                    <option selected><c:out value="${status.status}"/></option>
                </c:when>
                <c:otherwise>
                    <option><c:out value="${status.status}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select><br>
    <button type="submit">Save</button>

</form>
</body>
</html>
