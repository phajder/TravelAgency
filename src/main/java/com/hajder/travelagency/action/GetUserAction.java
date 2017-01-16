package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.UserDAO;
import com.hajder.travelagency.entity.User;
import com.hajder.travelagency.exception.DAOException;
import com.hajder.travelagency.model.SecurityUtil;

import java.sql.SQLException;

/**
 * Test action
 * Created by Piotr on 19.12.2016.
 * @author Piotr Hajder
 */
public class GetUserAction extends Action {
    private String username;
    private String password;

    private User user;

    @Override
    protected void doAction() throws DAOException {
        UserDAO dao = (UserDAO) daoFactory.getDaoInstance(UserDAO.class);
        String salt = dao.getSalt(username);
        password = SecurityUtil.generateHashedString(password, salt);
        user = dao.getUser(username, password);
    }

    public User getUser() {
        return user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
