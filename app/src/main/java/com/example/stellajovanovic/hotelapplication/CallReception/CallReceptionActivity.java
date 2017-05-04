package com.example.stellajovanovic.hotelapplication.CallReception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stellajovanovic.hotelapplication.R;

public class CallReceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_reception);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
