package com.example.stellajovanovic.hotelapplication.RoomService;

import android.graphics.drawable.Drawable;

public class Food {

    public String title;
    public Drawable productImage;
    public String description;
    public double price;
    public boolean selected;

    public Food(String title, Drawable productImage, String description,
                double price) {
        this.title = title;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
    }

}
