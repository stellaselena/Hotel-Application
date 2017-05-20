package com.example.stellajovanovic.hotelapplication.HotelInfo;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.MainMenu.MainActivity;
import com.example.stellajovanovic.hotelapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import layout.EmailFragment;
import layout.PhoneFragment;
import layout.WebsiteFragment;

public class HotelInformationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnPhone, btnEmail, btnWebsite, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_information);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnPhone = (Button) findViewById(R.id.buttonPhone);
        btnWebsite = (Button) findViewById(R.id.buttonWebsite);
        btnEmail = (Button) findViewById(R.id.buttonEmail);
        btnBack = (Button )findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelInformationActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void changeFragment(View view){
        FrameLayout fl = (FrameLayout)findViewById(R.id.fragment_place);

        if(view == btnPhone) {
             fl.removeAllViews();
             getFragmentManager().beginTransaction().replace(R.id.fragment_place, new PhoneFragment()).addToBackStack(null).commit();

         }
         if (view == btnEmail) {
             fl.removeAllViews();
             getFragmentManager().beginTransaction().replace(R.id.fragment_place, new EmailFragment()).addToBackStack(null).commit();

         }
        if (view == btnWebsite) {
            fl.removeAllViews();
            getFragmentManager().beginTransaction().replace(R.id.fragment_place, new WebsiteFragment()).addToBackStack(null).commit();

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng oslo = new LatLng(59.913869, 10.752245);
        mMap.addMarker(new MarkerOptions().position(oslo).title("Hotel").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).snippet("Download our application")).showInfoWindow();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oslo, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                if(arg0.getTitle().equals("Hotel"))
                    Toast.makeText(HotelInformationActivity.this, "Address: Osloveien 123, Oslo, Norge", Toast.LENGTH_LONG).show();
                return true;
            }

        });


    }


}
