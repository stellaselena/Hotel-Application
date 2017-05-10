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
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.R;

public class RoomServiceActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView)findViewById(R.id.textView);

        if (CartHelper.getCartList().size()!=0) {
            textView.setText("Thank you for ordering! \nYour order will arrive shortly.");
        } else {
            textView.setText("Your cart is empty. \nPlease select something from the menu before setting an order.");
        }
    }
}
