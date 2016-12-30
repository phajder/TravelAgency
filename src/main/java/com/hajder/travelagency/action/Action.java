package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.DAOFactory;
import com.hajder.travelagency.exception.DAOException;

/**
 * Database access actions
 * Created by Piotr on 19.12.2016.
 * @author Piotr Hajder
 */
public abstract class Action {
    private static final String DB_PROVIDER = "agency.jndi";

    protected DAOFactory daoFactory;

    Action() {
        daoFactory = DAOFactory.getInstance(DB_PROVIDER);
    }

    protected abstract void doAction() throws DAOException;

    public final void execute() throws DAOException {
        doAction();
    }
}
