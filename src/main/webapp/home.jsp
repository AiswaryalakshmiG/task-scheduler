<%@ page import="com.example.tasks.model.User" %>
<%
    User user = (User) session.getAttribute("authUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<style>
    body { font-family: Arial; background:#eef; padding:20px; }
    .top { display:flex; justify-content: space-between; }
    a.btn { padding:6px 10px; background:#444; color:white; text-decoration:none; }
</style>
</head>
<body>

<div class="top">
    <h3>Welcome, <%= user.getUsername() %></h3>

    <a class="btn" href="<%= request.getContextPath() %>/tasks?action=new">Create Task</a>
</div>

<p>Your task scheduler is ready.</p>

<a href="<%= request.getContextPath() %>/tasks">View Tasks</a>

</body>
</html>
