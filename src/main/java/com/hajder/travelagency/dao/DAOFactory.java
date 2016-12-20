package com.hajder.travelagency.dao;

import com.hajder.travelagency.exception.DAOConfigurationException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Database Access Objects Factory.
 * Created by Piotrek on 04.04.2016.
 * @author Piotr Hajder
 */
public abstract class DAOFactory {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_DRIVER = "driver";

    abstract Connection getConnection() throws SQLException;

    public static DAOFactory getInstance(String name) {
        if(name == null) {
            throw new DAOConfigurationException("Database name is null.");
        }

        DAOProperties properties = new DAOProperties(name);
        String url = properties.getProperty(PROPERTY_URL, true);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
        String password = properties.getProperty(PROPERTY_PASSWORD, false);
        String username = properties.getProperty(PROPERTY_USERNAME, password != null);
        DAOFactory instance;

        // If driver is specified, then load it to let it register itself with DriverManager.
        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new DAOConfigurationException(
                        "Driver class '" + driverClassName + "' is missing in classpath.", e);
            }
            instance = new DriverManagerDAOFactory(url, username, password);
        }

        // Else assume URL as DataSource URL and lookup it in the JNDI.
        else {
            DataSource dataSource;
            try {
                dataSource = (DataSource) new InitialContext().lookup(url);
            } catch (NamingException e) {
                throw new DAOConfigurationException(
                        "DataSource '" + url + "' is missing in JNDI.", e);
            }
            if (username != null) {
                instance = new DataSourceWithLoginDAOFactory(dataSource, username, password);
            } else {
                instance = new DataSourceDAOFactory(dataSource);
            }
        }

        return instance;
    }

    public DAO getDaoInstance(Class<? extends DAO> daoClass) {
        String daoClassName = daoClass.getName();
        DAO dao;
        try {
            dao = daoClass.cast(Class.forName(daoClassName).newInstance());
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("DAO class '" + daoClassName
                    + "' is missing in classpath.", e);
        } catch (IllegalAccessException e) {
            throw new DAOConfigurationException("DAO class '" + daoClassName
                    + "' cannot be accessed.", e);
        } catch (InstantiationException e) {
            throw new DAOConfigurationException("DAO class '" + daoClassName
                    + "' cannot be instantiated.", e);
        } catch (ClassCastException e) {
            throw new DAOConfigurationException("DAO class '" + daoClassName
                    + "' does not extend DAO class" + "'." , e);
        }
        dao.setDaoFactory(this);

        return dao;
    }
}
