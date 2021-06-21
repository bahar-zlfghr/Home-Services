<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> ${role} Saved Successfully </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

<h3> ${role} Saved Successfully. </h3>

<table class="table table-bordered table-striped text-info">
    <tr>
        <td> ${role} Name </td>
        <td> ${user.name} </td>
    </tr>

    <tr>
        <td> ${role} Family </td>
        <td> ${user.family} </td>
    </tr>

    <tr>
        <td> ${role} Email </td>
        <td> ${user.email} </td>
    </tr>
</table>

</body>
</html>
