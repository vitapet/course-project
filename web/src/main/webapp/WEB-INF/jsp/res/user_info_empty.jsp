<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<table>
    <tr>
        <td><label><spring:message code="registration.login"/></label></td>
        <td><form:input path="login"/></td>
        <td><form:errors path="login"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.password"/></label></td>
        <td><form:password path="password" id="password"/></td>
        <td><form:errors path="password"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.first.name"/></label></td>
        <td><form:input path="firstName" id="firstName"/></td>
        <td><form:errors path="firstName"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.surname"/></label></td>
        <td><form:input path="surname" id="surname"/></td>
        <td><form:errors path="surname"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.birthdate"/></label></td>
        <td><form:input path="birthDate" id="birthDate" type="date"/></td>
        <td><form:errors path="birthDate"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.phone"/></label></td>
        <td><form:input path="phone" id="phone"/></td>
        <td><form:errors path="phone"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.country"/></label></td>
        <td><form:input path="country" id="country"/></td>
        <td><form:errors path="country"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.city"/></label></td>
        <td><form:input path="city" id="city"/></td>
        <td><form:errors path="city"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.street"/></label></td>
        <td><form:input path="street" id="street"/></td>
        <td><form:errors path="street"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.house"/></label></td>
        <td><form:input path="house" id="house"/></td>
        <td><form:errors path="house"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.block"/></label></td>
        <td><form:input path="block" id="block"/></td>
        <td><form:errors path="block"/></td>
    </tr>
    <tr>
        <td><label><spring:message code="registration.apartment"/></label></td>
        <td><form:input path="apartment" id="apartment"/></td>
        <td><form:errors path="apartment"/></td>
    </tr>
</table>