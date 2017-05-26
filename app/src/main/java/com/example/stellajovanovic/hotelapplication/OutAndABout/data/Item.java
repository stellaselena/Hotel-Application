package com.example.stellajovanovic.hotelapplication.OutAndABout.data;

import org.json.JSONObject;

/**
 * Created by Stella on 25.05.2017.
 */

public class Item implements JSONPopulator {
    private Condition mCondition;

    public Condition getCondition() {
        return mCondition;
    }

    @Override
    public void populate(JSONObject data) {
        mCondition = new Condition();
        mCondition.populate(data.optJSONObject("condition"));

    }
}
