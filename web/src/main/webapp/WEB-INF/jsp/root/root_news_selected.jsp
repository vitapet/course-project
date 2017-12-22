<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Selected News</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/root/news">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<a href="${pageContext.request.contextPath}/root/news/update?id=${news.id}">Update</a><br>
<img src="${pageContext.request.contextPath}/image?name=${news.image}" name="track${news.id}"
     alt="Loading ${news.image}" width="600" height="400"><br>
<script language="JavaScript" type="text/javascript">
    function refreshImage() {
        console.log('refr');
        var image = "${pageContext.request.contextPath}//resources//image//${news.image}";
        console.log(document.images["track${news.id}"]);
        document.images["track${news.id}"].src = image;

    }

    setTimeout(refreshImage, 6000);
</script>
<label>Дата публикации:&nbsp;</label><c:out value="${news.timestamp}"/><br>
<c:out value="${news.name}"/><br>
<c:out value="${news.description}"/><br>
<label>Автор:&nbsp;</label><c:out value="${news.user.firstName}"/>&nbsp;<c:out
        value="${news.user.surname}"/><br>
<br>
<form action="${pageContext.request.contextPath}/root/news/selected" method="post">
    <input type="hidden" name="newsId" value="${news.id}">
    <p>Комментарии</p>
    <c:forEach items="${comments}" var="comment">
        <input type="radio" name="id" value="<c:out value="${comment.id}"/>">
        <c:out value="${comment.user.login}"/>&nbsp;
        <c:out value="${comment.date}"/><br>
        <c:out value="${comment.text}"/><br><br>
    </c:forEach>
    <c:if test="${not empty comments}">
        <button type="submit">Delete</button>
    </c:if>
</form>
</body>
</html>
