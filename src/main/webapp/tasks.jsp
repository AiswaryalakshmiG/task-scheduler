<%@ page import="java.util.*, com.example.tasks.model.Task"%>

<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    if (tasks == null) tasks = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
<title>Your Tasks</title>
<style>
body {
	font-family: Arial;
	padding: 20px;
}

table {
	border-collapse: collapse;
	width: 600px;
}

th, td {
	border: 1px solid #444;
	padding: 8px;
}
</style>
</head>
<body>

	<h2>Your Tasks</h2>

	<table>
		<tr>
			<th>Title</th>
			<th>Priority</th>
			<th>Due</th>
			<th>Status</th>
		</tr>

		<% for(Task task : tasks) { %>
		<tr>
			<td><%= task.getTitle() %></td>
			<td><%= task.getPriority() %></td>
			<td><%= task.getDueDateTime() %></td>
			<td><%= task.getStatus() %></td>

			<td><a href="tasks?action=edit&id=<%= task.getId() %>">Edit</a>
				| <a href="tasks?action=delete&id=<%= task.getId() %>"
				onclick="return confirm('Are you sure?')">Delete</a></td>
		</tr>
		<% } %>

	</table>

</body>
</html>
