package com.example.tastebud;

import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import android.content.Intent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Map extends AppCompatActivity  implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap gmap;
    String googlePlacesData;
    String url;
    private String m1;
    private String m2;
    TextView textView3;
    private static final String TAG = "Map";
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    Intent intent = getIntent();

    Bundle extras = intent.getExtras();
    //Text Box Stuff
    m1 = extras.getString("m1");
    m2 = extras.getString("m2");
    TextView textView = findViewById(R.id.textView);
    textView.setText(m1);

    TextView textView2 = findViewById(R.id.textView2);
    textView2.setText(m2);

    textView3 = findViewById(R.id.textView3);
    textView3.setText(m1);

    textView3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId == EditorInfo.IME_ACTION_SEARCH  || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.KEYCODE_ENTER
            ||event.getAction() == KeyEvent.ACTION_DOWN){
                //execute our method for searching

                geoLocate();

            }


            return false;
        }

    });


    //Google maps stuff
    Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");


        String searchString = textView3.getText().toString();

        Geocoder geocoder = new Geocoder(Map.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch(IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());

        }
        if(list.size() > 0){
            Address address = list.get(0);
            Log.d(TAG, "geoLocate: found a location: " + address.toString());

        }

    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {


        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + 2000);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyCEii9OEVbFtmLbat9F0-JbGv4uYFJ3nIY");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);

        LatLng ny = new LatLng(45.382376, -75.696263);
        url = getUrl(45.382376,-75.696263, m1 );

        gmap.addMarker(new MarkerOptions().position(ny).title("Home Base"));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));

        //Nearby Searches

    }

    public void onMapSearch(View view) {
        EditText locationSearch =  findViewById(R.id.textView3);

        String location = locationSearch.getText().toString();
        List<Address>addressList = null;

        if (location != null || !location.equals("")) {
            gmap.clear();
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            gmap.addMarker(new MarkerOptions().position(latLng).title(location));
            gmap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }
}
