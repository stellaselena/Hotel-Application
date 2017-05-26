package com.example.stellajovanovic.hotelapplication.OutAndABout.service;

import com.example.stellajovanovic.hotelapplication.OutAndABout.data.Channel;

/**
 * Created by Stella on 25.05.2017.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
