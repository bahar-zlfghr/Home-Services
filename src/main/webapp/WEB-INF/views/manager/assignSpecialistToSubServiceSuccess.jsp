<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Assign Specialist To Sub Service Successfully </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

<h3><button type="button" class="btn btn-lg btn-success" disabled>  Assign Specialist To Sub Service Successfully. </button></h3>

<table class="table table-bordered table-striped text-info">
    <tr>
        <td> Sub Service </td>
        <td> ${subService.name} </td>
    </tr>
    <tr>
        <td> Specialist </td>
        <td> ${specialist.name} ${specialist.family} </td>
    </tr>
</table>

</body>
</html>
