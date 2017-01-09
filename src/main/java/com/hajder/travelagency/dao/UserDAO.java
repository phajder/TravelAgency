package com.hajder.travelagency.dao;

import com.hajder.travelagency.entity.User;
import com.hajder.travelagency.model.UserRole;

import java.sql.*;

import static com.hajder.travelagency.dao.DAOUtil.setValues;

/**
 * Created by Piotr on 30.12.2016.
 * @author Piotr Hajder
 */
public class UserDAO extends DAO {
    private static final String SQL_VERIFY_USER =
            "SELECT user_role " +
            " FROM users " +
            " WHERE username = ? " +
            " AND password = ? ";
    private static final String SQL_GET_SALT =
            "SELECT salt " +
            " FROM users " +
            " WHERE username = ?";
    private static final String SQL_USER_UNIQUE = "SELECT COUNT(username) " +
            " FROM users " +
            " WHERE username = ?";
    private static final String SQL_REGISTER =
            "INSERT INTO users (username, password, salt, user_role, email) " +
            " VALUES (?,?,?, " + UserRole.USER.toString() + "?,?)";

    public String getSalt(String username) throws SQLException {
        Connection conn = daoFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SQL_GET_SALT);
        stmt.setObject(1, username);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public User getUser(String username, String hashedPassword) throws SQLException {
        User user = null;
        Connection conn = daoFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SQL_VERIFY_USER);
        setValues(stmt, username, hashedPassword);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            user = new User(username, hashedPassword);
            UserRole role = UserRole.getUserRole(rs.getString(1));
            user.setRole(role);
        }
        return user;
    }

    public boolean isUsernameUnique(String username) throws SQLException {
        Connection conn = daoFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SQL_USER_UNIQUE);
        setValues(stmt, username);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            int val = rs.getInt(1);
            return val == 0;
        }
        return false;
    }

    public void register(User user) throws SQLException {
        Connection conn = daoFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SQL_REGISTER);
        setValues(stmt,
                user.getUsername(),
                user.getPassword(),
                user.getSalt(),
                user.getEmail());
        stmt.execute();
    }
}
