package com.hajder.travelagency.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Piotr on 09.01.2017.
 * @author Piotr Hajder
 */
@ManagedBean
@RequestScoped
public class MapsBean {
    private String address;

    public String doSearch() {
        return null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
