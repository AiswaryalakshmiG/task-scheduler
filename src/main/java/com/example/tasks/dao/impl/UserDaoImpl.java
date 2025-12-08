package com.example.tasks.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.tasks.dao.UserDAO;
import com.example.tasks.model.User;
import com.example.tasks.util.DBConnection;

public class UserDaoImpl implements UserDAO {

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT * FROM app_user WHERE username = ?";
		//String sql = "SELECT username, password FROM app_user WHERE username = ?";
		try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, username);
	            ResultSet rs = ps.executeQuery();
	            
	            if(rs.next()) {
	            	User user= new User();
	            	user.setId(rs.getInt("id"));
	            	user.setUsername(rs.getString("username"));
	            	user.setPassword(rs.getString("password"));
	            	user.setEmail(rs.getString("email"));
	            	return user;
	            }
		} catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

}

