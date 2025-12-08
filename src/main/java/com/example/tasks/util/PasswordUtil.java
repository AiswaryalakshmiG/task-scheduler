package com.example.tasks.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	public static String hashPassword(String plainPassword) {
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
        System.out.println("Hashed password for DB: " + hashed);
        return hashed;
    }

    public static boolean checkPassword(String plainPassword, String hashed) {
        return BCrypt.checkpw(plainPassword, hashed);
    }
    public static void main(String[] args) {
        String plainPassword = "admin";
        hashPassword(plainPassword);
    }
}
