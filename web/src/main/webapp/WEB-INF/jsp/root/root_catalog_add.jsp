<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add goods</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/root/catalog">Return</a><br>
<form action="${pageContext.request.contextPath}/root/catalog/add" method="post">
    <label>Товар:&nbsp;</label><input type="text" required name="name" placeholder="Название"><br>
    <label>Описание:&nbsp;</label><input type="text" required name="description"><br>
    <label>Категория:&nbsp;</label>
    <select name="categorySelected" required>
        <c:forEach items="${categories}" var="category">
            <option><c:out value="${category.category}"/></option>
        </c:forEach>
    </select><br>
    <label>Количество:&nbsp;</label><input type="text" required name="count"><br>
    <label>Цена:&nbsp;</label><input type="text" required name="price"><br>
    <button type="submit">Save</button>

</form>
</body>
</html>
