package com.wroclaw.restoraunt.restoraunt.async;

import com.wroclaw.restoraunt.restoraunt.entity.Dish;
import com.wroclaw.restoraunt.restoraunt.entity.RestaurantStatus;

import java.util.Iterator;

/**
 * @author Dmytro Melnychuk
 */
public class OrderDescriptionCreator {
    private RestaurantStatus restaurantStatus;

    public OrderDescriptionCreator(RestaurantStatus restaurantStatus) {
        this.restaurantStatus = restaurantStatus;
    }

    public String create() {
        StringBuilder orderDescriptionBuilder = new StringBuilder();
        Iterator<Dish> dishIterator = restaurantStatus.getDishesDto().iterator();
        while (dishIterator.hasNext()) {
            Dish dish = dishIterator.next();
            orderDescriptionBuilder.append("Dish id: " + dish.getDishId());
            orderDescriptionBuilder.append("\n");
            orderDescriptionBuilder.append("Dish name: " + dish.getName());
            orderDescriptionBuilder.append("\n");
            orderDescriptionBuilder.append("Dish description: " + dish.getDescription());
            orderDescriptionBuilder.append("\n");
            if (dishIterator.hasNext()) {
                orderDescriptionBuilder.append("\n");
            }
        }
        return orderDescriptionBuilder.toString();
    }
}
