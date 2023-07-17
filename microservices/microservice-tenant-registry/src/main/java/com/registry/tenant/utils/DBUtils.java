package com.registry.tenant.utils;

import com.registry.tenant.models.Config;
import com.registry.tenant.models.Tenant;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * DB Utils class.
 * Provides utility functions to connect to db and run sql statements.
 *
 * */
public class DBUtils {
    public static Logger logger = Logger.getLogger(DBUtils.class.getName());

    /**
     * getConnection method. This connection can run sql statements.
     * @param config requires the config object
     * @return connection object
     * */
    private static Connection getConnection(Config config) throws ClassNotFoundException, SQLException {
        logger.info("Getting connection.");
        Connection connection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(config.getDatabase().getUrl(),
                config.getDatabase().getUsername(),
                config.getDatabase().getPassword());
        return connection;
    }

    /**
     * Run statement method. This method is used to run sql statements.
     *
     */
    public static List<Tenant> runStatement(Config config, String sqlStatement, List<Tenant> tenants)
            throws ClassNotFoundException, SQLException {
        logger.info("Running sql statement: " + sqlStatement);
        Connection connection = getConnection(config);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStatement);
        // Convert result set tenants object here.
        while (resultSet.next()) {
            tenants.add(Mapper.doMapping(resultSet));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return tenants;
    }

    public static Tenant runStatement(Config config, String sqlStatement)
            throws ClassNotFoundException, SQLException {
        logger.info("Running sql statement: " + sqlStatement);
        Connection connection = getConnection(config);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        Tenant tenant = null;
        // Convert result set tenants object here.
        while (resultSet.next()) {
            tenant = Mapper.doMapping(resultSet);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return tenant;
    }

    public static void runUpdateStatement(Config config, String sqlStatement)
            throws ClassNotFoundException, SQLException {
        logger.info("Running sql statement: " + sqlStatement);
        Connection connection = getConnection(config);
        Statement statement = connection.createStatement();
        int response = statement.executeUpdate(sqlStatement);
        if (response == 1) {
            logger.info("Successfully executed sql statement");
        } else {
            logger.info("Error executing sql statement");
            throw new SQLException();
        }
        statement.close();
        connection.close();
    }
}
