package com.hajder.travelagency.model;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Created by Piotr on 17.01.2017.
 * @author Piotr Hajder
 */
public class Localizator {
    private static final String apiKey = "AIzaSyAvhvFJR8JSLfr0VP9CjPMmqP6BPcNssdc";

    public static LatLng getCoordinates(String address) throws Exception {
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
        GeocodingResult[] results = GeocodingApi.newRequest(context)
                .address(address).await();
        if(results.length > 0) {
            return results[0].geometry.location;
        }
        return null;
    }
}
