package com.hajder.travelagency.bean;

import com.hajder.travelagency.action.FavouriteTransportAction;
import com.hajder.travelagency.entity.FavouritePublicTransport;
import com.hajder.travelagency.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
@ManagedBean(name = "favouriteTransport")
@RequestScoped
public class FavouriteTransportBean {
    private List<FavouritePublicTransport> favouritePublicTransports;

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        User user = (User) session.getAttribute("user");
        if(user != null) {
            FavouriteTransportAction action = new FavouriteTransportAction();
            action.setUsername(user.getUsername());
            action.execute();
            favouritePublicTransports = action.getFavouriteTransports();
        }
    }

    public List<FavouritePublicTransport> getFavouritePublicTransports() {
        return favouritePublicTransports;
    }
}
