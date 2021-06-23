<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Create Service </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <link href="${pageContext.request.contextPath}/static/css/error.css" rel="stylesheet">
</head>
<body>

<form:form  cssClass="p-3 m-3"
            method="post"
            action="/administrator/create/service"
            modelAttribute="serviceDto">
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td><form:label path="name"> Service Name </form:label></td>
            <td><form:input path="name" name="name"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:errors path="name" cssClass="error" class="error"/>
                <span class="error"> ${duplicateServiceName} </span>
            </td>
        </tr>

        <tr>
            <td colspan="1">
                <input type="submit" value="Crate">
            </td>
            <td></td>
        </tr>
    </table>
</form:form>

</body>
</html>
