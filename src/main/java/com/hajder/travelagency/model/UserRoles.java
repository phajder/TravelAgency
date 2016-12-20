package com.hajder.travelagency.model;

/**
 * Created by pioot on 19.12.2016.
 */
public enum UserRoles {
    USER("ACL_USER"), ADMIN("ACL_ADMIN"), SUPER_ADMIN("ACL_SUPERUSER");

    String name;

    UserRoles(String name) {
        this.name = name;
    }
}
