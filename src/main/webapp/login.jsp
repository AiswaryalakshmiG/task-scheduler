<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: Arial; background:#f0f0f0; }
        .box {
            width: 300px;
            background:white;
            padding:20px;
            margin:80px auto;
            border:1px solid #ccc;
            border-radius:5px;
        }
        input { width:100%; padding:8px; margin-top:8px; }
        button { padding:8px 15px; margin-top:10px; width:100%; }
    </style>
</head>
<body>

<div class="box">
    <h3>Login</h3>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>Username</label><br>
        <input type="text" name="username" required><br>

        <label>Password</label><br>
        <input type="password" name="password" required><br>

        <button type="submit">Login</button>
    </form>
</div>

</body>
</html>
