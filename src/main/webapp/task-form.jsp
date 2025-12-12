<%@ page import="com.example.tasks.model.Task" %>


<%
    // Read task object if edit action
    Task t = (Task) request.getAttribute("task");
    if (t == null) t = new Task();

    String dueVal = "";
    if (t.getDueDateTime() != null) {
        dueVal = t.getDueDateTime().toString();
        if (dueVal.length() > 16) dueVal = dueVal.substring(0,16);
    }

    String remVal = "";
    if (t.getReminderDateTime() != null) {
        remVal = t.getReminderDateTime().toString();
        if (remVal.length() > 16) remVal = remVal.substring(0,16);
    }
%>
<!DOCTYPE html>
<html>
<head>
<title>Create Task</title>
</head>

<body>
<h2><%= (t.getId() == 0) ? "Create New Task" : "Edit Task" %></h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form method="post" action="tasks">
    <input type="hidden" name="id" value="<%= t.getId() %>">

 <label>Title</label><br>
    <input type="text" name="title" required><br><br>
    <label>Priority</label><br>
    <select name="priority">
        <option <%= "Low".equals(t.getPriority()) ? "selected" : "" %>>Low</option>
        <option <%= "Medium".equals(t.getPriority()) ? "selected" : "" %>>Medium</option>
        <option <%= "High".equals(t.getPriority()) ? "selected" : "" %>>High</option>
    </select><br><br>

   <label>Due Date & Time</label><br>
    <input type="datetime-local" name="dueDateTime" value="<%= dueVal %>"><br><br>

    <label>Reminder</label><br>
    <input type="datetime-local" name="reminderDateTime" value="<%= remVal %>"><br><br>

    <label>Description</label><br>
    <textarea name="description"><%= t.getDescription() %></textarea><br><br>

    <button type="submit"><%= (t.getId()==0) ? "Create Task" : "Update Task" %></button>
</form>
</body>
</html>