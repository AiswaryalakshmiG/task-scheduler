<%@ page import="com.example.tasks.model.Task" %>
<!DOCTYPE html>
<html>
<head>
<title>Create Task</title>
</head>

<body>
<h2>Create New Task</h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form method="post" action="tasks">
 <label>Title</label><br>
    <input type="text" name="title" required><br><br>

    <label>Priority</label><br>
    <select name="priority">
        <option>Low</option>
        <option>Medium</option>
        <option>High</option>
    </select><br><br>

    <label>Due Date & Time</label><br>
    <input type="datetime-local" name="dueDateTime"><br><br>

    <label>Reminder</label><br>
    <input type="datetime-local" name="reminderDateTime"><br><br>

    <label>Description</label><br>
    <textarea name="description"></textarea><br><br>

    <button type="submit">Create Task</button>
</form>
</body>
</html>