package com.hajder.travelagency.entity;

import com.hajder.travelagency.model.UserRole;

/**
 * Created by Piotr on 19.12.2016.
 * @author Piotr Hajder
 */
public class User {
    private String username;
    private String password;
    private UserRole role;
    private String salt;
    private String email;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return !role.equals(UserRole.USER);
    }

    public boolean isSuperAdmin() {
        return role.equals(UserRole.SUPER_ADMIN);
    }
}
