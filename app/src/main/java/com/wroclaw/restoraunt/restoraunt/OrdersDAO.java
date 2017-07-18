package com.wroclaw.restoraunt.restoraunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dmytro Melnychuk
 */
public final class OrdersDAO {

    private final List<String> orderNames = new ArrayList<>();
    private final List<String> descriptions = new ArrayList<>();


    public OrdersDAO() {
        orderNames.addAll(Arrays.asList("Barshcz", "Halaba"));
        addDefaultDescriptions();
    }

    public boolean addOrder(String orderName, String description) {
        return orderNames.add(orderName) && descriptions.add(description);
    }

    public void removeOrder(int orderName) {
        orderNames.remove(orderName);
        descriptions.remove(orderName);
    }

    private void addDefaultDescriptions() {
        descriptions.addAll(Arrays.asList("So good soup", "Alale"));
    }

    public List<String> getOrderNames() {
        return orderNames;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }
}
