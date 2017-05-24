package com.example.stellajovanovic.hotelapplication.CheckIn;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.R;

import java.util.Date;
import java.util.Locale;

public class CheckInActivity extends AppCompatActivity {
    private static final String TAG = "CheckInActivity";


    CustomerSQLiteHelper db = new CustomerSQLiteHelper(this);
    TextView mTextName;
    Button mButtonChangeInfo;
    ImageButton mButtonCheckIn;
    Button mButtonCheckOut;
    TextView mTextCheckIn;
    Customer customer;
    Context context;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Date = "date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //db.onUpgrade(db.getWritableDatabase(), 1, 2);
        String room = getIntent().getStringExtra("roomnr");
        context = this;
        customer = new Customer();
        customer.setName("Stella");
        customer.setSurname("Selena");
        db.createCustomer(customer);

        if (room != null && db.getCustomer(1).getId() == 1) {
            customer.setName("Stella");
            customer.setSurname("Selena");
            customer.setRoomNumber(room);
            db.updateCustomer(customer);
        }

        mTextCheckIn = (TextView) findViewById(R.id.tvCheckIn);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        mTextCheckIn.setText(sharedpreferences.getString(Date, ""));
        mTextName = (TextView) findViewById(R.id.textViewName);
        mTextName.setText(db.getCustomer(1).getName());
        mButtonChangeInfo = (Button) findViewById(R.id.buttonChangeInfo);

        mButtonChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckInActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
        mButtonCheckIn = (ImageButton) findViewById(R.id.buttonCheckIn);
        mButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long ctm = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy 'at' HH:mm");
                Date date = new Date(ctm);
                customer.setCheckInDate(sdf.format(date));
                db.updateCustomer(customer);
                mTextCheckIn.setText(customer.getCheckInDate());

                String d = mTextCheckIn.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Date, d);
                editor.commit();
                Toast.makeText(getApplicationContext(), "You are now checked in",
                        Toast.LENGTH_LONG).show();
                mButtonCheckIn.setEnabled(false);

            }
        });
        mButtonCheckOut = (Button) findViewById(R.id.buttonCheckOut);
        mButtonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextCheckIn.getText().toString().contains("You")) {
                    Toast.makeText(getApplicationContext(), "You can't checkout because you are not checked in.",
                            Toast.LENGTH_LONG).show();
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle("Confirmation")
                            .setMessage("Are you sure you want to check out?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mTextCheckIn.setText("You haven't checked in yet");
                                    String d = mTextCheckIn.getText().toString();
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(Date, d);
                                    editor.commit();
                                    mButtonCheckIn.setEnabled(true);
                                    Toast.makeText(getApplicationContext(), "You have been checked out. We hope that you have enjoyed your stay.",
                                            Toast.LENGTH_LONG).show();
                                }

                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
            }
        });

    }
}

