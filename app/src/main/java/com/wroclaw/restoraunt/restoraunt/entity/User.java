package com.wroclaw.restoraunt.restoraunt.entity;

import java.util.Set;

public class User {

    private int userId;
    private String name;
    private String password;
    private String login;
    private Set<RestaurantStatus> orders;
    private Set<Comment> comments;
    private String address;

    public User(int userId, String name, String password, String login) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.login = login;
    }

    public User(int userId, String name, String password, String login, Set<RestaurantStatus> orderses) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.login = login;
        this.orders = orderses;
    }


}

