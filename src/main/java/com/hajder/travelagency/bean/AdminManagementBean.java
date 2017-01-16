package com.hajder.travelagency.bean;

import com.hajder.travelagency.action.ApiLocationAction;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
@ManagedBean(name = "adminManagement")
@RequestScoped
public class AdminManagementBean {
    private String location, cid;

    public void addLocation() {
        FacesMessage message;
        if(location != null && cid != null) {
            ApiLocationAction action = new ApiLocationAction();
            action.setLocation(location);
            action.setCid(cid);
            action.execute();
            //TODO: dodac lokalizowana wiadomosc
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Powodzenie!",
                    "Dodano lokacje do bazy.");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Blad!",
                    "Nalezy uzupelnic wszystkie dane!");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
