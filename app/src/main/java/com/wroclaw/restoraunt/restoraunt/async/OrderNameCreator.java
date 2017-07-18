package com.wroclaw.restoraunt.restoraunt.async;

import com.wroclaw.restoraunt.restoraunt.entity.Dish;
import com.wroclaw.restoraunt.restoraunt.entity.RestaurantStatus;

import java.util.Iterator;

/**
 * @author Dmytro Melnychuk
 */
public class OrderNameCreator {
    private RestaurantStatus restaurantStatus;

    public OrderNameCreator(RestaurantStatus restaurantStatus) {
        this.restaurantStatus = restaurantStatus;
    }

    public String create() {
        StringBuilder orderNameBuilder = new StringBuilder();
        Iterator<Dish> dishIterator = restaurantStatus.getDishesDto().iterator();
        while (dishIterator.hasNext()) {
            orderNameBuilder.append(dishIterator.next().getName());
            if (dishIterator.hasNext()) {
                orderNameBuilder.append(",");
            }
        }
        return orderNameBuilder.toString();
    }
}
