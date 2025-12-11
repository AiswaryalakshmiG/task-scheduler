package com.example.tasks.dao;

import com.example.tasks.model.Task;

public interface TaskDAO {
    boolean create(Task task);
}