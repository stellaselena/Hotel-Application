package com.example.stellajovanovic.hotelapplication.OutAndABout.data;


import org.json.JSONObject;

/**
 * Created by Stella on 25.05.2017.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");

    }
}
