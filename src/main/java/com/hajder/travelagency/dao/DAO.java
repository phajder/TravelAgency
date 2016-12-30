package com.hajder.travelagency.dao;

/**
 * Database Access Object model
 * Created by Piotr on 04.04.2016.
 * @author Piotr hajder
 */
public abstract class DAO {
    protected DAOFactory daoFactory;

    void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
