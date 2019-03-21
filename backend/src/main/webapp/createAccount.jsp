<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
    <h1>Welcome on the homepage</h1>
    <p>Please create an account:</p>

    <form method="post" action="${pageContext.request.contextPath}/account">
        <input type="text" name="username" placeholder="Enter username" /> <br />
        <input type="text" name="email_address" placeholder="Enter email address" /> <br />
        <input type="password" name="password" placeholder="Enter password" /> <br/>
        <input type="number" name="age" placeholder="Enter age" /> <br />

        <input type="checkbox" name="is_admin" id="isAdmin" />
        <label for="isAdmin">Admin privileges</label> <br/>

        <input type="submit" value="Create" />
    </form>
</body>
</html>
