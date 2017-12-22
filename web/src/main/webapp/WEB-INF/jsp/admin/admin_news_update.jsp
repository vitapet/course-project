<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Admin news update</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/admin/news/selected?id=${news.id}">Return</a><br>
<form action="${pageContext.request.contextPath}/admin/news/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<c:out value="${news.id}"/>"/>
    <img src="${pageContext.request.contextPath}/image?name=${news.image}" name="track${news.id}"
         alt="Loading ${news.image}" width="600" height="400"><br>
    <label>Фото:&nbsp;</label><input name="picture" type="file"><br>
    <label>Дата публикации:&nbsp;</label><input type="text" name="date" value="<c:out value="${news.timestamp}"/>"><br>
    <label>Заголовок:&nbsp;</label><input type="text" name="name" value="<c:out value="${news.name}"/>"><br>
    <label>Текст новости:&nbsp;</label><input type="text" name="description"
                                              value="<c:out value="${news.description}"/>"><br>
    <label>Автор:&nbsp;</label><input type="text" name="user" value="<c:out value="${news.author}"/>" disabled><br>
    <button type="submit">Save</button>
</form>
</body>
</html>
