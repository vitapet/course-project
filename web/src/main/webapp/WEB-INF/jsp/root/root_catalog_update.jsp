<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Goods Update</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/root/catalog">Return</a><br>
<form action="${pageContext.request.contextPath}/root/catalog/update" method="post">
    <input type="hidden" name="id" value="<c:out value="${goods.id}"/>"/>
    <label>Товар:&nbsp;</label><input type="text" required name="name" value="${goods.name}"><br>
    <label>Описание:&nbsp;</label><input type="text" required name="description" value="${goods.description}"><br>
    <label>Категория:&nbsp;</label>
    <select name="categorySelected" required>
        <c:forEach items="${categories}" var="category">
            <c:choose>
                <c:when test="${category eq goods.category}">
                    <option selected><c:out value="${category.category}"/></option>
                </c:when>
                <c:otherwise>
                    <option><c:out value="${category.category}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select><br>
    <label>Количество:&nbsp;</label><input type="text" required name="count" value="${goods.count}"><br>
    <label>Цена:&nbsp;</label><input type="text" required name="price" value="${goods.price}"><br>
    <label>Статус:&nbsp;</label>
    <select name="goodsStatus" required>
        <c:forEach items="${goodsStatuses}" var="status">
            <c:choose>
                <c:when test="${status eq goods.isDeleted}">
                    <option selected><c:out value="${status.isDeleted}"/></option>
                </c:when>
                <c:otherwise>
                    <option><c:out value="${status.isDeleted}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select><br>
    <button type="submit">Save</button>

</form>
</body>
</html>
