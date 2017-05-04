package com.example.stellajovanovic.hotelapplication.CheckIn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stellajovanovic.hotelapplication.R;

public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
