package com.example.tasks.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import com.example.tasks.model.Task;
import com.example.tasks.model.User;
import com.example.tasks.service.TaskService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class TaskServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskService service = new TaskService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("authUser");

		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		req.getRequestDispatcher("/task-form.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("authUser");

		Task task = new Task();
		task.setUserId(user.getId());
		task.setTitle(req.getParameter("title"));
		task.setDescription(req.getParameter("description"));
		task.setPriority(req.getParameter("periority"));
		task.setStatus("PENDING");

		String due = req.getParameter("dueDateTime");
		if (due != null && !due.isEmpty())
			task.setDueDateTime(LocalDateTime.parse(due));

		String rem = req.getParameter("reminderDateTime");
		if (rem != null && !rem.isEmpty())
			task.setReminderDateTime(LocalDateTime.parse(rem));

		String err = service.validate(task);
		if (err != null) {
			req.setAttribute("error", err);
			req.setAttribute("task", task);
			req.getRequestDispatcher("/task-form.jsp").forward(req, resp);
			return;
		}

		service.create(task);

		resp.sendRedirect("home.jsp");
	}
}
