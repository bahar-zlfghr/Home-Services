<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Filter Users </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <script src="${pageContext.request.contextPath}/static/js/filterUser.js"></script>
</head>
<body>

<form:form cssClass="m-5 p-5 text-center" action="/administrator/filter" method="get">
    <div class="table-responsive">
        <table class="table table-striped table-primary table-hover">
            <tr>
                <th>
                    <select name="role" class="form-select">
                        <option value=""> Role </option>
                        <option value="customer"> Customer </option>
                        <option value="specialist"> Specialist </option>
                    </select>
                </th>
                <th>
                    <input name="name" placeHolder=" Name"/>
                </th>
                <th>
                    <input name="family" placeHolder=" Family "/>
                </th>
                <th>
                    <input name="email" placeHolder=" Email "/>
                </th>
                <th class="specialist box">
                    <input name="specialty" placeHolder=" Specialty "/>
                </th>
                <th class="specialist box">
                    <input name="score" placeHolder=" Score "/>
                </th>

                <th>
                    <input type="submit" value="Search">
                </th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th> Name </th>
                <th> Family </th>
                <th> Email </th>
                <th> Password </th>
                <th> Role </th>
                <th> Status </th>
                <th> Balance </th>
                <th> Specialty </th>
                <th> Score </th>
            </tr>
            <c:forEach items="${users.customerDtos}" var="customer">
                <tr>
                    <td> ${customer.name} </td>
                    <td> ${customer.family} </td>
                    <td> ${customer.email} </td>
                    <td> ${customer.password} </td>
                    <td> ${customer.role} </td>
                    <td> ${customer.status} </td>
                    <td> ${customer.accountDto.balance} </td>
                    <td> - </td>
                    <td> - </td>
                </tr>
            </c:forEach>
            <c:forEach items="${users.specialistDtos}" var="specialist">
                <tr>
                    <td> ${specialist.name} </td>
                    <td> ${specialist.family} </td>
                    <td> ${specialist.email} </td>
                    <td> ${specialist.password} </td>
                    <td> ${specialist.role} </td>
                    <td> ${specialist.status} </td>
                    <td> ${specialist.accountDto.balance} </td>
                    <td> ${specialist.specialty} </td>
                    <td> ${specialist.score} </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form:form>

</body>
</html>
