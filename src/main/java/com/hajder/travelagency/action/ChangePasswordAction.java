package com.hajder.travelagency.action;

import com.hajder.travelagency.dao.UserDAO;
import com.hajder.travelagency.exception.DAOException;
import com.hajder.travelagency.model.SecurityUtil;

/**
 * Created by Piotr on 17.01.2017.
 * @author Piotr Hajder
 */
public class ChangePasswordAction extends Action {
    private String username, oldPassword, newPassword;

    private boolean result = false;

    @Override
    protected void doAction() throws DAOException {
        UserDAO dao = (UserDAO) daoFactory.getDaoInstance(UserDAO.class);
        String salt = dao.getSalt(username);
        String oldHashed = SecurityUtil.generateHashedString(oldPassword, salt);
        if(dao.getUser(username, oldHashed) != null) {
            String newHashed = SecurityUtil.generateHashedString(newPassword, salt);
            result = dao.changePassword(username, newHashed);
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
