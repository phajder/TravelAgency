package com.hajder.travelagency.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource database connection management without authorization.
 * Created by Piotrek on 27.04.2016.
 * @author Piotr hajder
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
