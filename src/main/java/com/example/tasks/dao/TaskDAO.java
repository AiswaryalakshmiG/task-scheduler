package com.example.tasks.dao;

import java.util.List;

import com.example.tasks.model.Task;

public interface TaskDAO {
	boolean create(Task task);

	List<Task> findAllByUser(int userId);

	Task findById(int id, int userId);

	boolean update(Task task);

	boolean delete(int id, int userId);

}