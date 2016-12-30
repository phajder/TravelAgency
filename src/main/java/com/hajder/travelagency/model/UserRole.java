package com.hajder.travelagency.model;

/**
 * Created by Piotr on 19.12.2016.
 * @author Piotr Hajder
 */
public enum UserRole {
    USER("ACL_USER"), ADMIN("ACL_ADMIN"), SUPER_ADMIN("ACL_SUPERUSER");

    String name;

    UserRole(String name) {
        this.name = name;
    }

    public static UserRole getUserRole(String role) {
        for(UserRole userRole: values()) {
            if(userRole.toString().equals(role))
                return userRole;
        }
        return null;
    }


    @Override
    public String toString() {
        return name;
    }
}
