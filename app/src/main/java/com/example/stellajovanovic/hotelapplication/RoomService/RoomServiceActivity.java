package com.example.stellajovanovic.hotelapplication.RoomService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.stellajovanovic.hotelapplication.R;

public class RoomServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
