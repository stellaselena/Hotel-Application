package com.example.stellajovanovic.hotelapplication.CallReception;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.stellajovanovic.hotelapplication.R;

public class CallReceptionActivity extends AppCompatActivity {


    private static final String TAG = "RoomServiceActivity";
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_reception);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (ImageButton) findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                String phone = "+00 123456789";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }

        });
    }
}
