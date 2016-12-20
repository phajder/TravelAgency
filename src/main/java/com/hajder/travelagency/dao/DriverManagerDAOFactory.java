package com.hajder.travelagency.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Typical jdbc database access.
 * Created by Piotrek on 27.04.2016.
 * @author Piotr Hajder
 */
public class DriverManagerDAOFactory extends DAOFactory {
    private String url;
    private String username;
    private String password;

    public DriverManagerDAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
