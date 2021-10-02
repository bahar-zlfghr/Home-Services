<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> ${customerDto.name} ${customerDto.family} Panel </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>
<c:if test="${customerDto ne null}">
    <h3 style="text-align: center">
        <button type="button" class="btn btn-lg btn-success" disabled> Welcome ${customerDto.name} ${customerDto.family} </button>
    </h3>

    <a type="button" class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/customer/create/order"> Create Order </a>
    <a type="button" class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/customer/logout" > Log Out </a>
</c:if>

<c:if test="${customerDto eq null}">
    <div>
        <h3 style="text-align: center"><button type="button" class="btn btn-lg btn-danger" disabled> Please first login! </button></h3>
    </div>
    <div>
        <h3 style="text-align: center"><a type="button" class="btn btn-lg btn-danger" href="${pageContext.request.contextPath}/customer/login"> Login </a></h3>
    </div>
</c:if>

</body>
</html>
