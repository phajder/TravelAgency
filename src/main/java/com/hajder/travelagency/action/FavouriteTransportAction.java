package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.FavouriteDAO;
import com.hajder.travelagency.dao.UserDAO;
import com.hajder.travelagency.entity.FavouritePublicTransport;
import com.hajder.travelagency.exception.DAOException;

/**
 * Created by pioot on 16.01.2017.
 */
public class FavouriteTransportAction extends Action {
    private FavouritePublicTransport fpt;
    private String username;

    @Override
    protected void doAction() throws DAOException {
        if(fpt != null && username != null) {
            UserDAO userDao = (UserDAO) daoFactory.getDaoInstance(UserDAO.class);
            FavouriteDAO dao = (FavouriteDAO) daoFactory.getDaoInstance(FavouriteDAO.class);

            long userId = userDao.getUserId(username);

            dao.saveFavourite(fpt, userId);
        }
    }

    public void setFpt(FavouritePublicTransport fpt) {
        this.fpt = fpt;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
