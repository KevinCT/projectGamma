package com.example.user.barqueue;

/**
 * Created by User on 4/27/2016.
 */
public class ProductItem {
    private String name;
    private int price;
    public ProductItem() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

}
