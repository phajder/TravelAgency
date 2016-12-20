package com.hajder.travelagency.bean;

import com.hajder.travelagency.action.GetUserAction;
import com.hajder.travelagency.entity.User;
import com.hajder.travelagency.model.UserRoles;
import com.hajder.travelagency.resource.NavigationTags;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * User managed bean used for user authorization, including admins and super-user
 * Created by Piotrek on 13.12.2016.
 * @author Piotr Hajder
 */
@ManagedBean
@RequestScoped
public class UserBean {
    private String username;
    private String password;

    public String login() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        //TODO: Zmienic sposob wybierania uzytkownika na pobieranego z bazy danych
        User user = new User(username, password);
        user.setRole(UserRoles.ADMIN);
        session.setAttribute("user", user);
        GetUserAction action = new GetUserAction();
        action.execute();
        return NavigationTags.SUCCESS;
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();

        return NavigationTags.SUCCESS;
    }

    public boolean isAdminLogged() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");
        return user != null && user.isAdmin();
    }

    public boolean isSuperAdmin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User user = (User) session.getAttribute("user");
        return user != null && user.isSuperAdmin();
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
}
