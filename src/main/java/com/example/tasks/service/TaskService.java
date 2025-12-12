package com.example.tasks.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.tasks.dao.TaskDAO;
import com.example.tasks.dao.impl.TaskDaoImpl;
import com.example.tasks.model.Task;

public class TaskService {
	private TaskDAO dao = new TaskDaoImpl();
	
	 public List<Task> list(int userId) {
	        return dao.findAllByUser(userId);
	    }

	    public Task get(int id, int userId) {
	        return dao.findById(id, userId);
	    }
	    
	    public boolean update(Task task) {
	        return dao.update(task);
	    }

	    public boolean delete(int id, int userId) {
	        return dao.delete(id, userId);
	    }
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
