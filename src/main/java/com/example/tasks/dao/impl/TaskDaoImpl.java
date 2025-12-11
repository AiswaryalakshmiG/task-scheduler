package com.example.tasks.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;

import com.example.tasks.dao.TaskDAO;
import com.example.tasks.model.Task;
import com.example.tasks.util.DBConnection;

public class TaskDaoImpl implements TaskDAO {

	@Override
	public boolean create(Task task) {
		String sql = "INSERT INTO task (user_id, title, description, priority, status, " +
                "due_datetime, reminder_datetime, created_on, updated_on) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
					ps.setInt(1, task.getUserId());
					ps.setString(2, task.getTitle());
					ps.setString(3, task.getDescription());
					ps.setString(4, task.getPriority());
					ps.setString(5, task.getStatus());
					
					if(task.getDueDateTime() != null) {
						ps.setTimestamp(6, Timestamp.valueOf(task.getDueDateTime()));

					}else {
					     ps.setNull(6, Types.TIMESTAMP);					}
					
					if(task.getReminderDateTime() != null) {
						ps.setTimestamp(7, Timestamp.valueOf(task.getReminderDateTime()));
					}else {
						ps.setNull(7, Types.TIMESTAMP);
					}
					
					return ps.executeUpdate() > 0;
				}catch(Exception e) {
					e.printStackTrace();
				}
		
		return false;
	}

    
}
