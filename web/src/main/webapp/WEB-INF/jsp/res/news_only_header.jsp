<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<img src="${pageContext.request.contextPath}/image?name=${tempNews.image}" name="track${tempNews.id}"
     alt="Loading ${tempNews.image}" width="600" height="400"><br>
<label>Дата публикации:&nbsp;</label><c:out value="${tempNews.timestamp}"/><br>
<c:out value="${tempNews.name}"/><br>
