package com.example.stellajovanovic.hotelapplication.MakeRequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stellajovanovic.hotelapplication.R;

public class MakeRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
