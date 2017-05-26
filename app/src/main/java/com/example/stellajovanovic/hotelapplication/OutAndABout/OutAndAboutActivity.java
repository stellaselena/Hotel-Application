package com.example.stellajovanovic.hotelapplication.OutAndABout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.OutAndABout.data.Channel;
import com.example.stellajovanovic.hotelapplication.OutAndABout.data.Item;
import com.example.stellajovanovic.hotelapplication.OutAndABout.service.WeatherServiceCallback;
import com.example.stellajovanovic.hotelapplication.OutAndABout.service.YahooWeatherService;
import com.example.stellajovanovic.hotelapplication.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by Stella on 25.05.2017.
 */

public class OutAndAboutActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconIv;
    TextView placeNameText, placeAddressText, placePhoneText, temperatureTv, conditionTv, locationTv;
    Button getPlaceButton, btnTA;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;
    private YahooWeatherService mService;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
        weatherIconIv = (ImageView) findViewById(R.id.weatherIconIv);
        temperatureTv = (TextView) findViewById(R.id.temperatureTv);
        conditionTv = (TextView) findViewById(R.id.conditionTv);
        locationTv = (TextView) findViewById(R.id.locationTv);

        placeNameText = (TextView) findViewById(R.id.tvPlaceName);
        placeAddressText = (TextView) findViewById(R.id.tvPlaceAddress);
        placePhoneText = (TextView) findViewById(R.id.tvPlacePhone);

        getPlaceButton = (Button) findViewById(R.id.btnGetPlace);
        btnTA = (Button) findViewById(R.id.btnTA);
        btnTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutAndAboutActivity.this, TripAdvisorActivity.class);
                startActivity(intent);
            }
        });
        getPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(OutAndAboutActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

        mService = new YahooWeatherService(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading");
        mDialog.show();
        mService.refreshWeather("Oslo, Norway");
    }

    @Override
    public void serviceSuccess(Channel channel){

        mDialog.hide();
        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_ " + channel.getItem().getCondition().getCode(), null, getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId, null);

        weatherIconIv.setImageDrawable(weatherIconDrawable);
        temperatureTv.setText(item.getCondition().getTemperature()+ "\u00B0"+channel.getUnits().getTemperature());
        conditionTv.setText(item.getCondition().getDescription());
        locationTv.setText(mService.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception){
        mDialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Missing location permission", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(OutAndAboutActivity.this, data);
                placeNameText.setText(place.getName());
                placeAddressText.setText(place.getAddress());
                placePhoneText.setText(place.getPhoneNumber());
            }
        }
    }
}