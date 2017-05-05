package com.example.stellajovanovic.hotelapplication.RoomService;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.stellajovanovic.hotelapplication.R;

public class RoomServiceActivity extends AppCompatActivity {

    private static final String TAG = "RoomServiceActivity";
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (ImageButton) findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                String phone = "+00 12345678";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }

        });
    }

}
