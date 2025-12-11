<%@ page import="java.util.*, com.example.tasks.model.Task" %>

<%
List<Task> tasks = (List<Task>) request.getAttribute("tasks");
if (tasks == null) tasks = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
<title>Tasks</title>
<style>
    body { font-family: Arial; padding:20px; background:#f0f0f0; }
    table { border-collapse: collapse; width:100%; background:white; }
    th, td { border:1px solid #ccc; padding:8px; }
    a.btn { padding:4px 8px; border:1px solid #333; text-decoration:none; font-size:14px; }
</style>
</head>
<body>

<h3>Your Tasks</h3>

<a href="tasks?action=new" class="btn">New Task</a>
<br><br>

<table>
    <tr>
        <th>Title</th>
        <th>Priority</th>
        <th>Due</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

    <% for (Task t : tasks) { %>
    <tr>
        <td><%= t.getTitle() %></td>
        <td><%= t.getPriority() %></td>
        <td><%= t.getDueDateTime() %></td>
        <td><%= t.getStatus() %></td>
        <td>
            <a class="btn" href="tasks?action=edit&id=<%= t.getId() %>">Edit</a>
            <a class="btn" href="tasks?action=delete&id=<%= t.getId() %>"
               onclick="return confirm('Delete task?')">Delete</a>
        </td>
    </tr>
    <% } %>

</table>

</body>
</html>
