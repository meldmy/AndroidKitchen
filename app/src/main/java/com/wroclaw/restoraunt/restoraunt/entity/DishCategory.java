package com.wroclaw.restoraunt.restoraunt.entity;

public enum DishCategory {

    MAINS("mains"),
    APPETIZER("appetizer"),
    DESSERT("dessert");

    private String category;

    DishCategory(String category) {
        this.category = category;
    }
}
