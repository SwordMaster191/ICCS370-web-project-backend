<html>
    <body>
        <h2>Login</h2>
        <p>${error}</p>

        <form action="/login" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Submit">
        </form>

        <form method="post">
        <button type="submit" name="register">Register</button>
        </form>

    </body>
</html>