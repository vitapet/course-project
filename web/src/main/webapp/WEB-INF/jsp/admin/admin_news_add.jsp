<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Add news</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/admin/news">Return</a><br>
<form action="${pageContext.request.contextPath}/admin/news/add" method="post" enctype="multipart/form-data">
    <label>Фото:&nbsp;</label><input name="picture" type="file" required alt="Loading..."><br>
    <label>Дата публикации:&nbsp;</label><input type="text" required name="date"><br>
    <label>Заголовок:&nbsp;</label><input type="text" required name="name"><br>
    <label>Текст новости:&nbsp;</label><input type="text" required name="description"><br>
    <label>Автор:&nbsp;</label><input type="text" required name="author"><br>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/admin/news">Return</a><br>
</form>
</body>
</html>
