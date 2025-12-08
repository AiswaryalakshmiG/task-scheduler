package com.example.tasks.dao;
import com.example.tasks.model.User;

public interface UserDAO {
	User findByUsername(String username);
}