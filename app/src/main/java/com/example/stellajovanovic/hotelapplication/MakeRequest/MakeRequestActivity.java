package com.example.stellajovanovic.hotelapplication.MakeRequest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.CheckIn.CustomerSQLiteHelper;
import com.example.stellajovanovic.hotelapplication.R;

public class MakeRequestActivity extends AppCompatActivity {

    CheckBox ONE, TWO, THREE, FOUR;
    CustomerSQLiteHelper db = new CustomerSQLiteHelper(this);
    TextView tvRoom;
    Button setOrder;
    ProgressBar mProgressBar;
    Integer count = 1;
    TextView orderStatus;
    boolean processing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvRoom = (TextView) findViewById(R.id.tvRoom);
        tvRoom.setText(db.getCustomer(1).getRoomNumber().toString());

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        orderStatus = (TextView) findViewById(R.id.statusTv);


        ONE = (CheckBox) findViewById(R.id.toiletries);
        TWO = (CheckBox) findViewById(R.id.minibar);
        THREE = (CheckBox) findViewById(R.id.pillows);
        FOUR = (CheckBox) findViewById(R.id.towels);
        setOrder = (Button) findViewById(R.id.orderBtn);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(processing == false) {
                    StringBuffer result = new StringBuffer();
                    boolean atLeastOneChecked = false;
                    if (ONE.isChecked()) {
                        result.append("Toileteries ordered\n");
                        atLeastOneChecked = true;

                    }
                    if (TWO.isChecked()) {
                        result.append("Minibar refill ordered\n");
                        atLeastOneChecked = true;

                    }
                    if (THREE.isChecked()) {
                        result.append("Extra pillows ordered\n");
                        atLeastOneChecked = true;

                    }
                    if (FOUR.isChecked()) {
                        result.append("Extra towels ordered\n");
                        atLeastOneChecked = true;

                    }
                    if (!atLeastOneChecked) {
                        Toast.makeText(MakeRequestActivity.this, "Please choose at least one item",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MakeRequestActivity.this, result.toString(),
                                Toast.LENGTH_LONG).show();
                    }


                    count = 1;
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(0);
                    switch (view.getId()) {
                        case R.id.orderBtn:
                            new OrderTask().execute(10);
                            break;
                    }
                } else if (processing == true) {
                    Toast.makeText(MakeRequestActivity.this, "You've already made an order",
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        setOrder.setOnClickListener(listener);

    }

    class OrderTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            processing = true;

            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(3000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Your order should arrive now!";
        }
        @Override
        protected void onPostExecute(String result) {
            mProgressBar.setVisibility(View.GONE);
            orderStatus.setText(result);
            setOrder.setText("Would you like to place a new order?");
            processing = false;

        }
        @Override
        protected void onPreExecute() {
            orderStatus.setText("Order accepted");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            orderStatus.setText("Your order should arrive shortly. Status: ");
            mProgressBar.setProgress(values[0]);
        }
    }

}
