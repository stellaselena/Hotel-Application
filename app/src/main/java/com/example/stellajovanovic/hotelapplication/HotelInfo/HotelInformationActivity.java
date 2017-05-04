package com.example.stellajovanovic.hotelapplication.HotelInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stellajovanovic.hotelapplication.R;

public class HotelInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
