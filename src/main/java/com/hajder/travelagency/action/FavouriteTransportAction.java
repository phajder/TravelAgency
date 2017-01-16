package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.FavouriteDAO;
import com.hajder.travelagency.dao.UserDAO;
import com.hajder.travelagency.entity.FavouritePublicTransport;
import com.hajder.travelagency.exception.DAOException;

import java.util.List;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
public class FavouriteTransportAction extends Action {
    private FavouritePublicTransport fpt;
    private String username;

    private List<FavouritePublicTransport> favouriteTransports;

    @Override
    protected void doAction() throws DAOException {
        if(username != null) {
            FavouriteDAO dao = (FavouriteDAO) daoFactory.getDaoInstance(FavouriteDAO.class);
            UserDAO userDao = (UserDAO) daoFactory.getDaoInstance(UserDAO.class);
            long userId = userDao.getUserId(username);

            if(fpt != null) {
                dao.saveFavourite(fpt, userId);
            } else {
                dao.getFavouriteTransports(userId);
            }
        }
    }

    public List<FavouritePublicTransport> getFavouriteTransports() {
        return favouriteTransports;
    }

    public void setFpt(FavouritePublicTransport fpt) {
        this.fpt = fpt;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
