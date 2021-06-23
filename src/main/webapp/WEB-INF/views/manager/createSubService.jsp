<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Create Service </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <script src="${pageContext.request.contextPath}/static/js/createSubService.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/error.css" rel="stylesheet">
</head>
<body>

<form:form  cssClass="p-3 m-3"
            method="post"
            action="/administrator/create/subservice"
            onsubmit="return validateForm()">
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td>
                <label> Service </label>
            </td>
            <td>
                <select class="form-select" name="serviceName" id="serviceName">
                    <option value=""> Select one... </option>
                    <c:forEach items="${services}" var="service">
                        <option value="${service.name}"> ${service.name} </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <span class="error" id="serviceError"></span>
            </td>
        </tr>

        <tr>
            <td><label> Name </label></td>
            <td><input name="subServiceName" id="subServiceName"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <span class="error" id="subServiceNameError"></span>
                <span class="error"> ${duplicateSubServiceName} </span>
            </td>
        </tr>

        <tr>
            <td><label> Base Price </label></td>
            <td><input type="number" name="basePrice" id="basePrice"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <span class="error" id="basePriceError"></span>
            </td>
        </tr>

        <tr>
            <td><label> Description (Optional) </label></td>
            <td><textarea name="description" id="description"></textarea></td>
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
