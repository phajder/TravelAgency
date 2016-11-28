package com.hajder.travelagency.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by pioot on 27.04.2016.
 */
public class DataSourceDAOFactory extends DAOFactory {
    private DataSource dataSource;

    public DataSourceDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
