package com.registry.tenant;

import com.registry.tenant.models.Config;
import com.registry.tenant.utils.ConfigReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {
    public static void main(String[] args) throws IOException {
        String configFilePath = "src/main/resources/application.yaml";
        Config config = new ConfigReader(configFilePath).readConfig();

        String username = config.getDatabase().getUsername();
        String password = config.getDatabase().getPassword();
        String dbUrl = config.getDatabase().getUrl();

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from TENANT");

            int code;
            String title;
            System.out.println("Resulting Set: " + resultSet.toString());

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
