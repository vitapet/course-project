<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>News</title>
</head>
<body>
<header>
    <jsp:include page="res/menu.jsp"/>
    <a type="button" href="registration"><spring:message code="button.registration"/></a>
</header>
<a href="<c:url value="/"/>"><spring:message code="button.return"/></a><br/>
<jsp:include page="res/news_with_comments.jsp"/>
</body>
</html>
