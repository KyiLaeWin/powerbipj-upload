package db_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databasetest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/powerbidb"; // Database URL
        String user = "root"; // replace with your actual username
        String password = "win3698@@"; // replace with your actual password
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
