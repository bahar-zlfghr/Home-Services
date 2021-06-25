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
    <link href="${pageContext.request.contextPath}/static/css/error.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/assignSpecialistToSubService.js"></script>
</head>
<body>

<form:form  cssClass="p-3 m-3"
            method="post"
            action="/administrator/add/specialist/subservice"
            onsubmit="return validate()">
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td>
                <label> Sub Service </label>
            </td>
            <td>
                <select class="form-select" name="subServiceName" id="subServiceName">
                    <option value=""> Select one... </option>
                    <c:forEach items="${subServices}" var="subService">
                        <option value="${subService.name}"> ${subService.name} </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <span class="error" id="subServiceError"></span>
            </td>
        </tr>

        <tr>
            <th></th>
            <th> Name </th>
            <th> Family </th>
            <th> Email </th>
            <th> Specialty </th>
            <th> Score </th>
            <th> Status </th>
        </tr>

        <c:forEach items="${specialists}" var="specialist">
            <tr>
                <td><div class="form-check"><input class="form-check-input" type="radio" name="specialistEmail" id="${specialist.email}" value="${specialist.email}"></div></td>
                <td> ${specialist.name} </td>
                <td> ${specialist.family} </td>
                <td> ${specialist.email} </td>
                <td> ${specialist.specialty} </td>
                <td> ${specialist.score} </td>
                <td> ${specialist.status} </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td>
                <span class="error" id="specialistError"></span>
            </td>
        </tr>

        <tr>
            <td colspan="1">
                <input type="submit" value="Add">
            </td>
            <td></td>
        </tr>
    </table>
</form:form>

</body>
</html>
