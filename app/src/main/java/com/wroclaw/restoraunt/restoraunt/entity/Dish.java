package com.wroclaw.restoraunt.restoraunt.entity;

public class Dish {

    private int dishId;
    private String name;
    private String description;
    private int ingridients;
    private String image;
    private double price;
    private DishCategory category;

    private int quantity;

    public Dish() {
    }

    public Dish(int dishId, String name, String description, int ingridients) {
        this.dishId = dishId;
        this.name = name;
        this.description = description;
        this.ingridients = ingridients;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIngridients() {
        return ingridients;
    }

    public void setIngridients(int ingridients) {
        this.ingridients = ingridients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\nDish{" +
                "\ndishId=" + dishId +
                "\n, name='" + name + '\'' +
                "\n, description='" + description + '\'' +
                "\n, ingridients=" + ingridients +
                "\n, image='" + image + '\'' +
                "\n, price=" + price +
                "\n, category=" + category +
                "\n}";
    }
}
