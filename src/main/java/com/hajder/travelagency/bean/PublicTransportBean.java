package com.hajder.travelagency.bean;

import com.hajder.travelagency.action.ApiLocationAction;
import com.hajder.travelagency.action.FavouriteTransportAction;
import com.hajder.travelagency.entity.FavouritePublicTransport;
import com.hajder.travelagency.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
@ManagedBean(name = "publicTransport")
@RequestScoped
public class PublicTransportBean {
    private Map<String, String> locations;
    private List<String> cities;

    private String location, startPoint, endPoint, hour;
    private Date date;
    private boolean avoidTransfer, mode;

    @PostConstruct
    public void init() {
        ApiLocationAction action = new ApiLocationAction();
        action.execute();
        locations = action.getLocations();
        cities = new ArrayList<>(locations.keySet());
    }

    public String findConnection() {
        //TODO: implementacja HTTP GET API jakdojade.pl
        return "";
    }

    public void addFavourite() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx
                .getExternalContext()
                .getSession(true);
        User user = (User) session.getAttribute("user");
        FacesMessage msg;
        if(user != null) {
            FavouritePublicTransport fpt = new FavouritePublicTransport();
            fpt.setStartPoint(startPoint);
            fpt.setEndPoint(endPoint);
            fpt.setHour(hour);
            fpt.setAvoidTransfer(avoidTransfer);
            fpt.setMode(mode);

            FavouriteTransportAction action = new FavouriteTransportAction();
            action.setFpt(fpt);
            action.setUsername(user.getUsername());
            action.execute();

            //TODO: lokalizacja wiadomosci
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sukces",
                    "Dodano do ulubionych.");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Blad",
                    "Musisz byc zalogowany aby dodac do ulubionych.");
        }
        ctx.addMessage(null, msg);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isAvoidTransfer() {
        return avoidTransfer;
    }

    public void setAvoidTransfer(boolean avoidTransfer) {
        this.avoidTransfer = avoidTransfer;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public List<String> getCities() {
        return cities;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
