package com.example.stellajovanovic.hotelapplication.OutAndABout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stellajovanovic.hotelapplication.R;

public class OutAndAboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_and_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
