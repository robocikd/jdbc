package pl.robocikd.jdbc;

import java.sql.*;

public class Jdbc {

    public static final String URL = "jdbc:mysql://localhost:3306/hr?serverTimezone=UTC";
    public static final String USER = "damian";
    public static final String PASSWORD = "damian";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                System.out.println(id + ": " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
