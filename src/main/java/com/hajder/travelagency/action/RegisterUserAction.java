package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.UserDAO;
import com.hajder.travelagency.entity.User;
import com.hajder.travelagency.exception.DAOException;
import com.hajder.travelagency.model.SecurityUtil;

import java.sql.SQLException;

/**
 * Created by Piotr on 09.01.2017.
 * @author Piotr Hajder
 */
public class RegisterUserAction extends Action {
    private User user;

    @Override
    protected void doAction() throws DAOException {
        String salt = SecurityUtil.generateNewSalt();
        String newPassword = SecurityUtil.generateHashedString(user.getPassword(), salt);
        user.setPassword(newPassword);
        user.setSalt(salt);

        UserDAO dao = (UserDAO) daoFactory.getDaoInstance(UserDAO.class);
        dao.register(user);
    }

    public boolean isUsernameUnique(String username) throws SQLException {
        UserDAO dao = (UserDAO) daoFactory.getDaoInstance(UserDAO.class);
        return dao.isUsernameUnique(username);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
