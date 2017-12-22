package com.gmail.vitaliapetsenak.shop.repository;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionRepository {
    private static volatile ConnectionRepository instance;
    private BasicDataSource dataSource;

    private ConnectionRepository() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("DataBase");
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(resourceBundle.getString("driver.class.name"));
        dataSource.setUrl(resourceBundle.getString("url"));
        dataSource.setUsername(resourceBundle.getString("user"));
        dataSource.setPassword(resourceBundle.getString("password"));
        dataSource.addConnectionProperty("useUnicode", "yes");
        dataSource.addConnectionProperty("characterEncoding", "utf8");
    }

    public static synchronized ConnectionRepository getInstance() {
        if (instance == null) {
            instance = new ConnectionRepository();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection() throws SQLException {
        dataSource.close();
    }
}
