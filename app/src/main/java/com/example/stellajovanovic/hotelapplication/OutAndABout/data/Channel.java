package com.example.stellajovanovic.hotelapplication.OutAndABout.data;

import org.json.JSONObject;

/**
 * Created by Stella on 25.05.2017.
 */

public class Channel implements JSONPopulator {

    private Units units;
    private Item item;
    private String location;

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

    }
}
