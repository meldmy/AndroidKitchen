package com.wroclaw.restoraunt.restoraunt.entity;

public class Comment {

    private int commId;
    private Dish dishes;
    private String commentText;
    private int commentRating;
    private int commentDate;
    private int photoPath;
    private User author;

    public Comment(int commId, Dish dishes, String commentText, int commentRating, int commentDate, int photoPath) {
        this.commId = commId;
        this.dishes = dishes;
        this.commentText = commentText;
        this.commentRating = commentRating;
        this.commentDate = commentDate;
        this.photoPath = photoPath;
    }


}

