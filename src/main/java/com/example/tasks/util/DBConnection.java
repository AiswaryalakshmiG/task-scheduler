package com.example.tasks.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBConnection {
	private static final Log LOG = LogFactory.getLog(DBConnection.class);
	   private static String URL;
	    private static String USER;
	    private static String PASSWORD;
	static {
        try {
        	// Load PostgreSQL driver
        	Class.forName("org.postgresql.Driver");
        	 Properties props = new Properties();
        	 try (InputStream input = DBConnection.class
        		        .getClassLoader()
        		        .getResourceAsStream("application.properties")) {

                 if (input == null) {
                     throw new RuntimeException("classpath not found");
                 }

                 props.load(input);
                 
                 URL = props.getProperty("db.url");
                 USER = props.getProperty("db.username");
                 PASSWORD = props.getProperty("db.password");

                 if (URL == null || USER == null || PASSWORD == null) {
                     throw new RuntimeException("Missing DB properties");
                 }

				LOG.info("Database properties loaded successfully");
				LOG.info("DB URL: " + URL);
			}
    } catch (IOException | ClassNotFoundException e) {
        throw new RuntimeException("Failed to load DB configuration", e);
    }
	}
	public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        LOG.info("Connected to PostgreSQL successfully");
        return con;
    }
}
