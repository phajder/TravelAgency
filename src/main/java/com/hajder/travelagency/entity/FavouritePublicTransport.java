package com.hajder.travelagency.entity;

/**
 * Created by Piotr on 16.01.2017.
 * @author Piotr Hajder
 */
public class FavouritePublicTransport {
    private long favouriteId;
    private String startPoint, endPoint, hour;
    private boolean avoidTransfer, mode;

    public FavouritePublicTransport(){

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

    public long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(long favouriteId) {
        this.favouriteId = favouriteId;
    }
}
