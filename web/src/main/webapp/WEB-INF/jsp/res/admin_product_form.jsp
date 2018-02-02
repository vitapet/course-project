<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<form:form method="post" modelAttribute="product">
    <table>
        <tr>
            <td><label>Name</label></td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><label>Description</label></td>
            <td><form:input path="description"/></td>
            <td><form:errors path="description"/></td>
        </tr>
        <tr>
            <td><label>Category</label></td>
            <td><form:select path="category">
                <form:options items="${categories}"/>
            </form:select></td>
        </tr>
        <tr>
            <td><label>Count</label></td>
            <td><form:input path="count"/></td>
            <td><form:errors path="count"/></td>
        </tr>
        <tr>
            <td><label>Price</label></td>
            <td><form:input path="price"/></td>
            <td><form:errors path="price"/></td>
        </tr>
        <tr>
            <td><form:hidden path="id"/></td>
            <td><form:hidden path="status"/></td>
        </tr>
    </table>
    <button type="submit">Save</button>
</form:form>
