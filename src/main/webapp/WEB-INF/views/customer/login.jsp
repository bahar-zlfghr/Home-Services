<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Login Customer </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <link href="${pageContext.request.contextPath}/static/css/error.css" rel="stylesheet">
</head>
<body>

<c:if test="${logout eq true}">
    <h3 style="text-align: center">
        <button type="button" class="btn btn-lg btn-success" disabled> Log Out Successfully </button>
    </h3>
</c:if>

<form:form method='POST'
           action="/customer/login"
           modelAttribute="customerDto"
           cssClass="p-3 m-3">
    <table class="table">
        <tr>
            <td><form:label path="email"> Email </form:label></td>
            <td><form:input path="email" name="email"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:errors path="email" cssClass="error" class="error"/>
            </td>
        </tr>

        <tr>
            <td><form:label path="password"> Password </form:label></td>
            <td><form:input path="password" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:errors path="password" cssClass="error" class="error"/>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <span class="error"> ${errorLogin} </span>
            </td>
        </tr>

        <tr>
            <td colspan="1">
                <input type="submit" value="Login">
            </td>
            <td></td>
        </tr>
    </table>
</form:form>

</body>
</html>
