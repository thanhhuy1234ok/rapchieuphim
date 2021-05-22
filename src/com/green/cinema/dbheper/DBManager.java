package com.green.cinema.dbheper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION =
            "jdbc:mysql://localhost/cinema?user=root&password=huy123456789";

    private Connection connection = null;

    public DBManager() {
    }

    public Connection getDBConnection() {

        if (connection != null) {
            return connection;
        }
        try {
            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(DB_CONNECTION);
            System.out.println("get db connection success");

        } catch (ClassNotFoundException exception) {
            System.out.println("Could not load db driver");
        } catch (SQLException exception) {
            System.out.println("Make connection ex: " + exception.getMessage());
        }

        return connection;
    }
}
