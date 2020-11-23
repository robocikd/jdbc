package pl.robocikd.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDataSource {

    public static final String DATABASE_NAME = "hr";
    public static final String LOCALHOST = "localhost";
    public static final String USER = "damian";
    public static final String PASSWORD = "damian";

    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setServerName(LOCALHOST);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        try {
            dataSource.setServerTimezone("UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            while (resultSet.next()){
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                System.out.println(name + " " + lastName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
