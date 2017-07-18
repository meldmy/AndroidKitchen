package com.wroclaw.restoraunt.restoraunt.entity;

import java.util.List;

public class RestaurantStatus {

    private OrderDto orderDto;
    private List<Dish> dishesDto;

    private UserNameDto userNameDto;

    public RestaurantStatus() {
    }

    public RestaurantStatus(OrderDto orderDto, List<Dish> dishesDto, UserNameDto userNameDto) {
        this.orderDto = orderDto;
        this.dishesDto = dishesDto;
        this.userNameDto = userNameDto;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public List<Dish> getDishesDto() {
        return dishesDto;
    }

    public void setDishesDto(List<Dish> dishesDto) {
        this.dishesDto = dishesDto;
    }

    public UserNameDto getUserNameDto() {
        return userNameDto;
    }

    public void setUserNameDto(UserNameDto userNameDto) {
        this.userNameDto = userNameDto;
    }

    @Override
    public String toString() {
        return "RestaurantStatus{" +
                "\norderDto=" + orderDto +
                "\n, dishesDto=" + dishesDto +
                "\n}";
    }
}

