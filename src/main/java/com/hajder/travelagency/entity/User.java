package com.hajder.travelagency.entity;

import com.hajder.travelagency.model.UserRoles;

/**
 * Created by pioot on 19.12.2016.
 */
public class User {
    private long id;
    private String username;
    private String password;
    private UserRoles role;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
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

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return !role.equals(UserRoles.USER);
    }

    public boolean isSuperAdmin() {
        return role.equals(UserRoles.SUPER_ADMIN);
    }
}
