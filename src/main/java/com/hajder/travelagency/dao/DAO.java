package com.hajder.travelagency.dao;

/**
 * Created by Piotrek on 04.04.2016.
 */
public abstract class DAO {
    protected DAOFactory daoFactory;

    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
