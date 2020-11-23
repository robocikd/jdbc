package pl.robocikd.prepared;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySqlConnector {

    private final String URL = "jdbc:mysql://localhost:3306";
    private Properties properties;

    public void createDB() throws SQLException {
        setupProperties();
        Connection connection = DriverManager.getConnection(URL, properties);
        Statement statement = connection.createStatement();
        statement.execute("CREATE SCHEMA `HelloWorld`");
        connection.close();
    }

    public void createTable() throws SQLException {
        setupProperties();
        Connection connection = DriverManager.getConnection(URL + "/HelloWorld", properties);
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE `Employees` (\n" +
                "  `id` INT NOT NULL,\n" +
                "  `first` VARCHAR(45) NULL,\n" +
                "  `last` VARCHAR(45) NULL,\n" +
                "  `age` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`));");
        connection.close();
    }

    public void insertData(int userId, String first, String last, String age) throws SQLException {
        setupProperties();
        Connection connection = DriverManager.getConnection(URL + "/HelloWorld", properties);
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO HelloWorld.Employees (id, " +
                "first, " +
                "last, " +
                "age) " +
                "VALUES (" + userId + ", " +
                "'" + first + "', " +
                "'" + last + "', " +
                "'" + age + "');");
        connection.close();
    }

    public void insertPreparedStatementData(int userId, String first, String last, String age) throws SQLException {
        setupProperties();
        Connection connection = DriverManager.getConnection(URL + "/HelloWorld", properties);
        PreparedStatement statement = connection
                .prepareStatement("INSERT INTO HelloWorld.Employees (id,first,last,age) " +
                        "VALUES (?,?,?,?)");
        statement.setInt(1,userId);
        statement.setString(2,first);
        statement.setString(3,last);
        statement.setString(4,age);
        statement.execute();
        connection.close();
    }

    public List<Person> getDataFromDb(String dbName) throws SQLException {
        setupProperties();
        Connection connection = DriverManager.getConnection(URL+"/"+dbName,properties);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `Employees`;");
        List<Person> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("first"),
                    resultSet.getString("last"),
                    resultSet.getString("age")
            ));
        }
        return list;
    }

    private void setupProperties() {
        properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "password");
        properties.put("serverTimezone", "UTC");
    }

}
