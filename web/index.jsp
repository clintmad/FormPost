
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clint Week 5</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/main.css" type="text/css">
</head>

<body class="container-fluid">
    <div class="text-center row">
        <h1 class="heading">Students</h1>
    </div>
    <div class="row">
        <div class="col-xs-4"></div>
        <div class="col-xs-4">
        <form method="post" action ="http://localhost:7070/formpost2">
            <div class="form-group">
                <label class="label">First Name:</label>
                <input type="text" name="fName" class="form-control">
            </div>
            <div class="form-group">
                <label class="label">Last Name:</label>
                <input type="text" name="lName" class="form-control">
            </div>
            <div class="form-group">
                <label class="label">Nickname:</label>
                <input type="text" name="nName" class="form-control">
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>

        </form>
        </div>
        <div class="col-xs-4"></div>
    </div>
</body>
</html>
