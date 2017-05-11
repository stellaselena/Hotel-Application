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

import com.example.stellajovanovic.hotelapplication.CheckIn.CustomerSQLiteHelper;
import com.example.stellajovanovic.hotelapplication.R;

public class RoomServiceActivity extends AppCompatActivity {

    private static final String TAG = "RoomServiceActivity";
    CustomerSQLiteHelper db = new CustomerSQLiteHelper(this);
    TextView textView;
    TextView tvRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) findViewById(R.id.textView);
        tvRoom = (TextView)findViewById(R.id.tvRoomNr);
        if(db.getCustomer(1).getRoomNumber() != null){
            tvRoom.setText("Room number: " + db.getCustomer(1).getRoomNumber().toString());

        } else {
            tvRoom.setText(" ");
        }
        StringBuffer stringBuffer = new StringBuffer();
        double total = 0;
        if (CartHelper.getCartList().size() != 0) {
            stringBuffer.append("Your order: \n\n");
            for (int i = 0; i < CartHelper.getCartList().size(); i++) {

                stringBuffer.append(CartHelper.getCartList().get(i).title.toString());
                stringBuffer.append(" " + CartHelper.getCartList().get(i).price + " NOK");
                stringBuffer.append("\n");
                total += CartHelper.getCartList().get(i).price;


            }
            stringBuffer.append("\nTotal: ");
            stringBuffer.append(total + " NOK\n");
            stringBuffer.append("Thank you for ordering!");
            textView.setText(stringBuffer);

        } else {
            textView.setText("Your cart is empty. \nPlease select something from the menu before setting an order.");
        }
    }
}
