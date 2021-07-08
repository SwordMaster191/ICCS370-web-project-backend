<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.sql.*"%>

localhost:8080/home

<html>

<head>
<title>Booking Page</title>
</head>

<body>

<h1>Welcome to the Booking Page</h1>
<h2>Hello ${name}</h2>
<h2>Current user: ${username}</h2>

<form method="post">
<table border="2">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Username</td>
        <td>Password</td>
        <td>Email</td>
    </tr>

    <%
        try {
            String url = "jdbc:mysql://webappproject.culiz6fsdfka.ap-southeast-1.rds.amazonaws.com:3306/UserDatabase?autoReconnect=true&useSSL=false";
            String username = "admin";
            String password = "rootroot";
            Integer num = 0;
            String query = "select * from Users";
            ResultSet resultSet = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
    %>

    <tr>
            <% num++; %>
            <td> <%= resultSet.getString("id")%> </td>
            <td> <%= resultSet.getString("name")%> </td>
            <td> <%= resultSet.getString("username")%> </td>
            <td> <%= resultSet.getString("password")%> </td>
            <td> <%= resultSet.getString("email")%> </td>
    </tr>

    <br><a href="/logout">logout</a>

    <%  }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
</form>

</body>

</html>

