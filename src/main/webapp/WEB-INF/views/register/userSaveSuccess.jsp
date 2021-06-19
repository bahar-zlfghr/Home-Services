<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> ${role} Saved Successfully </title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>

<h3> ${role} Saved Successfully. </h3>

<strong> ${role} Name:  ${user.name}</strong><br>
<strong> ${role} Family:  ${user.family}</strong><br>
<strong> ${role} Email:  ${user.email}</strong><br>

</body>
</html>
