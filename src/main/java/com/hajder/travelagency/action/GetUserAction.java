package com.hajder.travelagency.action;

import com.hajder.travelagency.exception.DAOException;

/**
 * Test action
 * Created by Piotrek on 19.12.2016.
 * @author Piotr Hajder
 */
public class GetUserAction extends Action {
    @Override
    protected void doAction() throws DAOException {
        System.out.println("Hello action!");
    }
}
