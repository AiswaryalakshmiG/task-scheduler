package com.example.tasks.service;

import java.time.LocalDateTime;

import com.example.tasks.dao.TaskDAO;
import com.example.tasks.dao.impl.TaskDaoImpl;
import com.example.tasks.model.Task;

public class TaskService {
	private TaskDAO dao = new TaskDaoImpl();
	 public String validate(Task task) {

	        if (task.getTitle() == null || task.getTitle().trim().isEmpty())
	            return "Title is required";

	        if (task.getDueDateTime() != null &&
	        		task.getDueDateTime().isBefore(LocalDateTime.now()))
	            return "Due date cannot be in past";

	        return null;
	    }

	    public boolean create(Task task) {
	    	task.setStatus("PENDING");
	        return dao.create(task);
	    }
}
