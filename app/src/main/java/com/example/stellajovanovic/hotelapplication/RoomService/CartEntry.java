package com.example.stellajovanovic.hotelapplication.RoomService;

public class CartEntry {

    private Food mFood;
    private int mQuantity;

    public CartEntry(Food food, int quantity) {
        mFood = food;
        mQuantity = quantity;
    }

    public Food getFood() {
        return mFood;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

}
