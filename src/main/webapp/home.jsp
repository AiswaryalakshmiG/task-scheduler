<%@ page import="com.example.tasks.model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("authUser");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Home - Task Scheduler</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Welcome, <%= user.getUsername() %></h3>
    <%-- <form action="<%= request.getContextPath() %>/logout" method="post">
      <button class="btn btn-outline-secondary">Logout</button>
    </form> --%>
  </div>

  <p>Welcome</p>
</div>
</body>
</html>
