package com.wroclaw.restoraunt.restoraunt.json;

import android.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wroclaw.restoraunt.restoraunt.entity.RestaurantStatus;

import java.io.IOException;

/**
 * @author Dmytro Melnychuk
 */
public class RestaurantStatusCreator {

    private String restaurantStatusJson;

    public RestaurantStatusCreator(String originalResult) {

        restaurantStatusJson = originalResult;
    }

    public RestaurantStatus create() {
        try {
            return new ObjectMapper().readValue(restaurantStatusJson, RestaurantStatus.class);
        } catch (IOException e) {
            Log.d(getClass().getSimpleName(), "RestaurantStatus hasn't parsed");
        }
        return new RestaurantStatus();
    }
}
