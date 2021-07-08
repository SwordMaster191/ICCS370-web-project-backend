<html>
<body>
<h2>Registration</h2>
<p>
    ${usernameAlreadyExists}
    ${idAlreadyExists}
    ${emailAlreadyExists}
</p>

<form action="/register" method="post">
    Username:<br/>
    <input type="text" name="username"/>
    <br/>
    Password:<br/>
    <input type="password" name="password">
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>