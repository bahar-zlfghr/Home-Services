<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Registration Page </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo="> <%-- fix favicon.ico error!--%>
    <link href="${pageContext.request.contextPath}/static/css/registration.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/js/registration.js"></script>
</head>
<body>

<form:form  cssClass="p-3 m-3"
            method="post"
            action="/user/registration"
            modelAttribute="userDto"
            enctype="multipart/form-data">
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td><form:label path="name"> Name </form:label></td>
            <td><form:input path="name" name="name"/></td>
        </tr>
        <tr>
            <td></td>
            <td><form:errors path="name" cssClass="error" class="error"/></td>
        </tr>

        <tr>
            <td><form:label path="family"> Family </form:label></td>
            <td><form:input path="family" name="family"/></td>
        </tr>
        <tr>
            <td></td>
            <td><form:errors path="family" cssClass="error" class="error"/></td>
        </tr>

        <tr>
            <td><form:label path="email"> Email </form:label></td>
            <td><form:input path="email" name="email"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:errors path="email" cssClass="error" class="error"/>
                <span class="error"> ${duplicateEmailError} </span>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="password"> Password </form:label>
            </td>
            <td><form:input path="password" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:errors path="password" cssClass="error" class="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="role"> Role </form:label>
            </td>
            <td>
                <form:select path="role" class="form-select" name="role" id="role">
                    <form:option value=""> Select one... </form:option>
                    <form:option value="customer"> Customer </form:option>
                    <form:option value="specialist"> Specialist </form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:errors path="role" cssClass="error" class="error"/>
            </td>
        </tr>

        <tr class="specialist box">
            <td><label> Specialty </label></td>
            <td><input name="specialty" id="specialty"/></td>
        </tr>
        <tr class="specialist box">
            <td></td>
            <td>
                <div class="error" id="specialtyError">${notEmptySpecialtyError}</div>
            </td>
        </tr>

        <tr class="specialist box">
            <td>
                <label> Profile picture </label>
            </td>
            <td><input type="file" name="profilePicture" id="profilePicture" onchange="return checkFormatAndSizeProfilePicture()"></td>
        </tr>

        <tr class="specialist box">
            <td></td>
            <td>
                <div id="profilePicturePreview"></div>
                <div class="error" id="profilePictureError">${notEmptyProfilePictureError}</div>
            </td>
        </tr>

        <tr>
            <td colspan="1">
                <input type="submit" value="Register">
            </td>
            <td></td>
        </tr>
    </table>
</form:form>

</body>
</html>
