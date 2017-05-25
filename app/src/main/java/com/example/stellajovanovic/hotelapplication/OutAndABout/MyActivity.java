package com.example.stellajovanovic.hotelapplication.OutAndABout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.MainMenu.MainActivity;
import com.example.stellajovanovic.hotelapplication.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by Stella on 25.05.2017.
 */

public class MyActivity extends AppCompatActivity {


        TextView placeNameText, placeAddressText,placePhoneText;
        Button getPlaceButton, btnTA;
        private final static int MY_PERMISSION_FINE_LOCATION = 101;
        private final static int PLACE_PICKER_REQUEST = 1;


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
            placeNameText = (TextView) findViewById(R.id.tvPlaceName);
            placeAddressText = (TextView) findViewById(R.id.tvPlaceAddress);
            placePhoneText = (TextView) findViewById(R.id.tvPlacePhone);

            getPlaceButton = (Button) findViewById(R.id.btnGetPlace);
            btnTA = (Button)findViewById(R.id.btnTA);
            btnTA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyActivity.this, OutAndAboutActivity.class);
                    startActivity(intent);
                }
            });
            getPlaceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    try {
                        Intent intent = builder.build(MyActivity.this);
                        startActivityForResult(intent, PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        private void requestPermission() {

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
            if (requestCode == PLACE_PICKER_REQUEST){
                if (resultCode == RESULT_OK){
                    Place place = PlacePicker.getPlace(MyActivity.this, data);
                    placeNameText.setText(place.getName());
                    placeAddressText.setText(place.getAddress());
                    placePhoneText.setText(place.getPhoneNumber());
                }
            }
        }
    }