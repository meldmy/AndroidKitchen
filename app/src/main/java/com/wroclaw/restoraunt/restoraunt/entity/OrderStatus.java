package com.wroclaw.restoraunt.restoraunt.entity;

public enum OrderStatus {

    NEW("new"),
    INPROGRESS("inprogress"),
    COMPLETED("completed"),
    DELIVERED("delivered");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

}
