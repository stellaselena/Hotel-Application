package com.example.stellajovanovic.hotelapplication.CheckIn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.R;

import org.w3c.dom.Text;

/**
 * Created by Stella on 06.05.2017.
 */

public class CustomerActivity extends AppCompatActivity {
    TextView customerName;
    TextView customerSurname;
    TextView roomNumber;
    Customer selectedCustomer;
    CustomerSQLiteHelper db;
    Spinner mSpinner;
    String[] available_rooms;
    private boolean isSpinnerSelected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        available_rooms = getResources().getStringArray(R.array.available_rooms);

        customerName = (TextView) findViewById(R.id.tvName);
        customerSurname = (TextView) findViewById(R.id.tvSurname);
        roomNumber = (TextView) findViewById(R.id.tvRoom);
        mSpinner = (Spinner) findViewById(R.id.spinner);


        db = new CustomerSQLiteHelper(getApplicationContext());
        selectedCustomer = db.getCustomer(1);
        customerName.setText(selectedCustomer.getName());
        customerSurname.setText(selectedCustomer.getSurname());
        roomNumber.setText(selectedCustomer.getRoomNumber());

        ArrayAdapter aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, available_rooms);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(aa);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isSpinnerSelected == false) {
                    isSpinnerSelected = true;
                } else {
                    String selected = parent.getItemAtPosition(position).toString();
                    selectedCustomer.setRoomNumber(selected);
                    db.updateCustomer(selectedCustomer);
                    Toast.makeText(getApplicationContext(), "Your room number has been updated.", Toast.LENGTH_SHORT).show();
                    roomNumber.setText(selectedCustomer.getRoomNumber());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO: 07.05.2017  
            }
        });
    }


    public void update(View v) {
        /*selectedCustomer.setRoomNumber(((EditText) findViewById(R.id.roomEdit)).getText().toString());
        db.updateCustomer(selectedCustomer);
        Toast.makeText(getApplicationContext(), "Your room number has been updated.", Toast.LENGTH_SHORT).show();*/


    }
}
