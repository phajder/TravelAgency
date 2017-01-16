package com.hajder.travelagency.bean;

import com.hajder.travelagency.action.ApiLocationAction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

    private String location;
    private String startPoint, endPoint;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Map<String, String> getLocations() {
        return locations;
    }
}
