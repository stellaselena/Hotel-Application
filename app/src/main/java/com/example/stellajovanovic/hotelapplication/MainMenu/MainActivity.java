package com.example.stellajovanovic.hotelapplication.MainMenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.stellajovanovic.hotelapplication.CallReception.CallReceptionActivity;
import com.example.stellajovanovic.hotelapplication.CheckIn.CheckInActivity;
import com.example.stellajovanovic.hotelapplication.HotelInfo.HotelInformationActivity;
import com.example.stellajovanovic.hotelapplication.MakeRequest.MakeRequestActivity;
import com.example.stellajovanovic.hotelapplication.OutAndABout.OutAndAboutActivity;
import com.example.stellajovanovic.hotelapplication.R;
import com.example.stellajovanovic.hotelapplication.RoomService.RoomServiceActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ImageButton mImageButton1, mImageButton2, mImageButton3, mImageButton4, mImageButton5, mImageButton6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mImageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        mImageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        mImageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        mImageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        mImageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        mImageButton6 = (ImageButton) findViewById(R.id.imageButton6);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_checkin) {

            Intent startNewActivity = new Intent(this, CheckInActivity.class);
            startActivity(startNewActivity);

        } else if (id == R.id.make_request) {

            Intent startNewActivity = new Intent(this, MakeRequestActivity.class);
            startActivity(startNewActivity);
        } else if (id == R.id.room_service) {

            Intent startNewActivity = new Intent(this, RoomServiceActivity.class);
            startActivity(startNewActivity);
        } else if (id == R.id.out_and_about) {

            Intent startNewActivity = new Intent(this, OutAndAboutActivity.class);
            startActivity(startNewActivity);
        } else if (id == R.id.call_reception) {

            Intent startNewActivity = new Intent(this, CallReceptionActivity.class);
            startActivity(startNewActivity);
        } else if (id == R.id.hotel_info) {

            Intent startNewActivity = new Intent(this, HotelInformationActivity.class);
            startActivity(startNewActivity);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sendToCheckIn(View view) {
        Intent intent = new Intent(MainActivity.this, CheckInActivity.class);
        startActivity(intent);
    }

    public void sendToMakeReq(View view) {
        Intent intent = new Intent(MainActivity.this, MakeRequestActivity.class);
        startActivity(intent);
    }

    public void sendToRoomSer(View view) {
        Intent intent = new Intent(MainActivity.this, RoomServiceActivity.class);
        startActivity(intent);
    }

    public void sendToOut(View view) {
        Intent intent = new Intent(MainActivity.this, OutAndAboutActivity.class);
        startActivity(intent);
    }

    public void sendToCallRec(View view) {
        Intent intent = new Intent(MainActivity.this, CallReceptionActivity.class);
        startActivity(intent);
    }

    public void sendToHotelInfo(View view) {
        Intent intent = new Intent(MainActivity.this, HotelInformationActivity.class);
        startActivity(intent);
    }


}
