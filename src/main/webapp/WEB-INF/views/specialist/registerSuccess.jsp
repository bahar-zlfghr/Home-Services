<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Specialist Saved Successfully </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>

<h3 style="text-align: center">
    <button type="button" class="btn btn-lg btn-success" disabled> Specialist Saved Successfully. </button>
</h3>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col"> Name </th>
            <th scope="col"> Family </th>
            <th scope="col"> Email </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td> ${specialistDto.name} </td>
            <td> ${specialistDto.family} </td>
            <td> ${specialistDto.email} </td>
        </tr>
    </tbody>
</table>

</body>
</html>
