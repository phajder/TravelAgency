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
            " VALUES (?,?,?,?,?)";

    public String getSalt(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_GET_SALT);
            setValues(stmt, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            //TODO: tomcat logging exception
        } finally {
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
            try { if(rs != null) rs.close(); } catch (SQLException ignored){}
        }
        return null;
    }

    public User getUser(String username, String hashedPassword) {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_VERIFY_USER);
            setValues(stmt, username, hashedPassword);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(username, hashedPassword);
                UserRole role = UserRole.getUserRole(rs.getString(1));
                user.setRole(role);
            }
        } catch (SQLException e) {
            //TODO: tomcat logging exception
        } finally {
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
            try { if(rs != null) rs.close(); } catch (SQLException ignored){}
        }
        return user;
    }

    public boolean isUsernameUnique(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_USER_UNIQUE);
            setValues(stmt, username);
            rs = stmt.executeQuery();
            if(rs.next()) {
                int val = rs.getInt(1);
                return val == 0;
            }
        } catch (SQLException e) {
            //TODO: tomcat logging exception
        } finally {
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
            try { if(rs != null) rs.close(); } catch (SQLException ignored){}
        }
        return false;
    }

    public boolean register(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = daoFactory.getConnection();
            stmt = conn.prepareStatement(SQL_REGISTER);
            setValues(stmt,
                    user.getUsername(),
                    user.getPassword(),
                    user.getSalt(),
                    UserRole.USER.toString(),
                    user.getEmail());
            int val = stmt.executeUpdate();
            return val == 1;
        } catch (SQLException e) {
            //TODO: tomcat logging exception
        } finally {
            try { if(conn != null) conn.close(); } catch (SQLException ignored){}
            try { if(stmt != null) stmt.close(); } catch (SQLException ignored){}
        }
        return false;
    }
}
