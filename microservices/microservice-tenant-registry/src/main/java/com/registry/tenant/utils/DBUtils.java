package com.registry.tenant.utils;

import com.registry.tenant.models.Config;

import java.sql.*;

/**
 * DB Utils class.
 * Provides utility functions to connect to db and run sql statements.
 *
 * */
public class DBUtils {

    /**
     * getConnection method. This connection can run sql statements.
     * @param config requires the config object
     * @return connection object
     * */
    public static Connection getConnection(Config config) throws ClassNotFoundException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(config.getDatabase().getUrl(),
                    config.getDatabase().getUsername(),
                    config.getDatabase().getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * Run statement method. This method is used to run sql statements.
     *
     */
    public static ResultSet runStatement(Config config, String sqlStatement)
            throws ClassNotFoundException, SQLException {
        Connection connection = getConnection(config);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStatement);
        statement.close();
        connection.close();
        return resultSet;
    }


}
