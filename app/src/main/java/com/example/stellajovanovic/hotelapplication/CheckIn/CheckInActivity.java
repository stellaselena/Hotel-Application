package com.example.stellajovanovic.hotelapplication.CheckIn;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.R;

import java.util.Locale;

public class CheckInActivity extends AppCompatActivity {


    CustomerSQLiteHelper db = new CustomerSQLiteHelper(this);
    TextView mTextName;
    Button mButtonChangeInfo;
    ImageButton mButtonCheckIn;
    Button mButtonCheckOut;
    TextView mTextCheckIn;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mTextCheckIn = (TextView) findViewById(R.id.tvCheckIn);

        Calendar c = Calendar.getInstance();
        String getCurrentDateTime = sdf.format(c.getTime());
        String getCheckInDateTime = sdf.format(myCalendar.getTime());

        if (getCurrentDateTime.compareTo(getCheckInDateTime) < 0)

        {
            mTextCheckIn.setText(sdf.format(myCalendar.getTime()));
            Toast.makeText(getApplicationContext(), "You are now checked in",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Select a correct date",
                    Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //db.onUpgrade(db.getWritableDatabase(), 1, 2);


        String room = getIntent().getStringExtra("roomnr");

        Customer customer = new Customer();
        customer.setName("Stella");
        customer.setSurname("Selena");
        db.createCustomer(customer);

        if(room!=null && db.getCustomer(1).getId() == 1) {
            customer.setName("Stella");
            customer.setSurname("Selena");
            customer.setRoomNumber(room);
            db.updateCustomer(customer);
        }


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
        mTextCheckIn = (TextView) findViewById(R.id.tvCheckIn);
        mTextCheckIn.setText("You haven't checked in yet");
        mButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CheckInActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        mButtonCheckOut = (Button) findViewById(R.id.buttonCheckOut);
        mButtonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextCheckIn.getText().toString().contains("You")){
                    Toast.makeText(getApplicationContext(), "You can't checkout because you are not checked in.",
                            Toast.LENGTH_LONG).show();
                } else {
                    mTextCheckIn.setText("You haven't checked in yet");
                    Toast.makeText(getApplicationContext(), "You have been checked out. We hope that you have enjoyed your stay.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}

