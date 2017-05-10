package com.example.stellajovanovic.hotelapplication.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.res.Resources;

import com.example.stellajovanovic.hotelapplication.R;

public class CartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Food> catalog;
    private static Map<Food, CartEntry> cartMap = new HashMap<Food, CartEntry>();

    public static List<Food> getCatalog(Resources res) {
        if (catalog == null) {
            catalog = new Vector<Food>();
            catalog.add(new Food("Pizza", res
                    .getDrawable(R.drawable.pizzares),
                    "Thin crust italian pizza with tomato sauce, cheese and salami", 200));
            catalog.add(new Food("Burger", res
                    .getDrawable(R.drawable.burgerres),
                    "Burger with ground beef, cheese and topped with salad, served on a toasted bun", 150));
            catalog.add(new Food("Club sandwich", res
                    .getDrawable(R.drawable.sandwichres),
                    "Three slices of club sandwich with turkey, ham and cheese with salad and tomatoes", 100));
        }

        return catalog;
    }

    public static void setQuantity(Food food, int quantity) {
        CartEntry curEntry = cartMap.get(food);
        if (quantity <= 0) {
            if (curEntry != null)
                removeProduct(food);
            return;
        }

        if (curEntry == null) {
            curEntry = new CartEntry(food, quantity);
            cartMap.put(food, curEntry);
            return;
        }

        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Food food) {
        CartEntry curEntry = cartMap.get(food);

        if (curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Food food) {
        cartMap.remove(food);
    }

    public static List<Food> getCartList() {
        List<Food> cartList = new Vector<Food>(cartMap.keySet().size());
        for (Food p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}
