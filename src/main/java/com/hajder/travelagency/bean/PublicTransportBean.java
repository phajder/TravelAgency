package com.hajder.travelagency.bean;

import com.hajder.travelagency.action.ApiLocationAction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Map;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
@ManagedBean(name = "publicTransport")
@RequestScoped
public class PublicTransportBean {
    private Map<String, String> locations;

    private String location;
    private String startPoint, endPoint;

    @PostConstruct
    public void init() {
        ApiLocationAction action = new ApiLocationAction();
        action.execute();
        locations = action.getLocations();
    }

    public String findConnection() {
        //TODO: implementacja HTTP GET API jakdojade.pl
        return "";
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

    public Map<String, String> getLocations() {
        return locations;
    }
}
