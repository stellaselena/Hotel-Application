package com.example.stellajovanovic.hotelapplication.OutAndABout.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.stellajovanovic.hotelapplication.OutAndABout.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

/**
 * Created by Stella on 25.05.2017.
 */

public class YahooWeatherService {
    private WeatherServiceCallback mServiceCallback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback serviceCallback) {
        mServiceCallback = serviceCallback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather( String l) {
        this.location = location;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    error = e;

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null) {
                    mServiceCallback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");
                    if (count == 0) {
                        mServiceCallback.serviceFailure(new LocationWeatherException("No weather information found for " + location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    mServiceCallback.serviceSuccess(channel);
                } catch (JSONException e) {
                    mServiceCallback.serviceFailure(e);
                }
            }
        }.execute(location);

    }

    public class LocationWeatherException extends Exception {
        public LocationWeatherException(String message) {
            super(message);
        }
    }

}
