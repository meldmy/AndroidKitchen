package com.wroclaw.restoraunt.restoraunt.entity;

import java.util.Date;

/**
 * @author Dmytro Melnychuk
 */
public class OrderDto {
    private int orderId;
    private int sum;
    private Date orderDate;

    public OrderDto() {
    }

    public OrderDto(int orderId, int sum, Date orderDate) {
        this.orderId = orderId;
        this.sum = sum;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "\nOrderDto{" +
                "\norderId=" + orderId +
                "\n, sum=" + sum +
                "\n, orderDate=" + orderDate +
                "\n}";
    }
}
