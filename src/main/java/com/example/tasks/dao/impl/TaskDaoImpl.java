package com.example.tasks.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.tasks.dao.TaskDAO;
import com.example.tasks.model.Task;
import com.example.tasks.util.DBConnection;

public class TaskDaoImpl implements TaskDAO {

	private Task map(ResultSet rs) throws Exception {
		Task task = new Task();
		task.setId(rs.getInt("id"));
		task.setUserId(rs.getInt("user_id"));
		task.setTitle(rs.getString("title"));
		task.setDescription(rs.getString("description"));
		task.setPriority(rs.getString("priority"));
		task.setStatus(rs.getString("status"));

		Timestamp due = rs.getTimestamp("due_datetime");
		if (due != null)
			task.setDueDateTime(due.toLocalDateTime());

		Timestamp rem = rs.getTimestamp("reminder_datetime");
		if (rem != null)
			task.setReminderDateTime(rem.toLocalDateTime());

		return task;
	}

	@Override
	public boolean create(Task task) {
		String sql = "INSERT INTO task (user_id, title, description, priority, status, "
				+ "due_datetime, reminder_datetime, created_on, updated_on) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, task.getUserId());
			ps.setString(2, task.getTitle());
			ps.setString(3, task.getDescription());
			ps.setString(4, task.getPriority());
			ps.setString(5, task.getStatus());

			if (task.getDueDateTime() != null) {
				ps.setTimestamp(6, Timestamp.valueOf(task.getDueDateTime()));

			} else {
				ps.setNull(6, Types.TIMESTAMP);
			}

			if (task.getReminderDateTime() != null) {
				ps.setTimestamp(7, Timestamp.valueOf(task.getReminderDateTime()));
			} else {
				ps.setNull(7, Types.TIMESTAMP);
			}

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Task> findAllByUser(int userId) {
		List<Task> list = new ArrayList<>();
		String sql = "SELECT * FROM task WHERE user_id = ? ORDER BY due_datetime ASC";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(map(rs));
			}

		} catch (Exception e) {

		}
		return list;
	}

	@Override
	public Task findById(int id, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Task task) {
		String sql = "UPDATE task SET title=?, description=?, priority=?, status=?, " +
                "due_datetime=?, reminder_datetime=?, updated_on=NOW() " +
                "WHERE id=? AND user_id=?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setString(3, task.getPriority());
			ps.setString(4, task.getStatus());
			
			if(task.getDueDateTime() != null) 
				ps.setTimestamp(5, Timestamp.valueOf(task.getDueDateTime()));
			
			else
				ps.setNull(5, Types.TIMESTAMP);
			
			if(task.getReminderDateTime() != null)
				ps.setTimestamp(5, Timestamp.valueOf(task.getReminderDateTime()));
			else
				ps.setNull(6, Types.TIMESTAMP);
			
			ps.setInt(7, task.getId());
	        ps.setInt(8, task.getUserId());

	        return ps.executeUpdate() > 0;
			
		}catch(Exception e) {
			  e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
