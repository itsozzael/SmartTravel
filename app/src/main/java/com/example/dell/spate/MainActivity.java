package com.example.dell.spate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


public class MainActivity extends AppCompatActivity {

    TextView placeNameText;
    TextView placeaddressText;
    WebView attributionText;
    Button getPlaceButton;
    TextView placeReview;
    TextView placeURItext;
    TextView placePhoneNumber;
    TextView placeLatitude;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        placeNameText = (TextView) findViewById(R.id.tvPlaceName);
        placeReview = (TextView) findViewById(R.id.tvPlaceReview);
        placeaddressText = (TextView) findViewById(R.id.tvPlaceAddress);
        placeURItext= (TextView) findViewById(R.id.tvPlaceURI);
        placePhoneNumber= (TextView)findViewById(R.id.tvPlacePhone);
        //placeLatitude=(TextView)findViewById(R.id.tvPlacelat);
        attributionText = (WebView) findViewById(R.id.wvAttribution);
        getPlaceButton = (Button) findViewById(R.id.SearchPlace);

        getPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder= new PlacePicker.IntentBuilder();
                try {
                    Intent intent= builder.build(MainActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void requestPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
          if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
          {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
          }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case MY_PERMISSION_FINE_LOCATION:
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Give location permissions please", Toast.LENGTH_LONG).show();
                    finish();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PLACE_PICKER_REQUEST) {
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(MainActivity.this, data);
                CharSequence mane= place.getName();


                placeNameText.setText(place.getName());
                placeaddressText.setText(place.getAddress());
                //final LatLng location = place.getLatLng();
               //int var= (int)place.getRating();
                if((String.valueOf(place.getRating())).equals("-1.0"))
                {
                    placeReview.setText("No Ratings!");
                }
                else {
                    placeReview.setText(String.valueOf(place.getRating()));
                }
                if((String.valueOf(place.getWebsiteUri())).equals("null"))
                {
                    placeURItext.setText("No website!");
                }
                else {
                    placeURItext.setText(String.valueOf(place.getWebsiteUri()));
                }
                if((String.valueOf(place.getPhoneNumber())).equals("null"))
                {
                    placePhoneNumber.setText("No Phone Number!");

                }else {
                    placePhoneNumber.setText(String.valueOf(place.getPhoneNumber()));
                }
               // placeLatitude.setText(String.valueOf(place.getLocale()));
                //System.out.println("Hi");
               // System.out.println((int) place.getRating());

                if(place.getAttributions() == null){
                    attributionText.loadData("no attribution", "text/html: charset=utf-8", "UTF-8" );
                }
                else {
                    attributionText.loadData(place.getAttributions().toString(), "text/html: charset=utf-8", "UTF-8" );
                }
            }
        }
    }


}
