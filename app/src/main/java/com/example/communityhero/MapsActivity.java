package com.example.communityhero;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final int REQUEST_LOCATION = 1;

    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    private void saveCurrentUserLocation() {

        if(ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location != null){
                ParseGeoPoint currentUserLocation = new ParseGeoPoint(location.getLatitude(), location.getLongitude());

                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    currentUser.put("Location", currentUserLocation);
                    currentUser.saveInBackground();
                } else {
                    alertDisplayer("Well... you're not logged in...","Login first!");
                    Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
            else {
                Log.d("userLocation", "Unable to find current user location.");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                saveCurrentUserLocation();
                break;
        }
    }

    private ParseGeoPoint getCurrentUserLocation(){
        // saving the currentUserLocation to allow it's return
        saveCurrentUserLocation();

        // finding currentUser
        ParseUser currentUser = ParseUser.getCurrentUser();

        // if it's not possible to find the user, return to login
        if (currentUser == null) {
            alertDisplayer("Well... you're not logged in...","Login first!");
            Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        // otherwise, return the current user location
        return currentUser.getParseGeoPoint("Location");


    }

    private void showCurrentUserInMap(final GoogleMap googleMap){

        ParseGeoPoint currentUserLocation = getCurrentUserLocation();

        // creating a marker in the map showing the current user location
        LatLng currentUser = new LatLng(currentUserLocation.getLatitude(), currentUserLocation.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(currentUser).title(ParseUser.getCurrentUser().getUsername()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        // zoom the map to the currentUserLocation
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUser, 5));
    }


    private void showClosestUser(final GoogleMap googleMap){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNear("Location", getCurrentUserLocation());
        // setting the limit of near users to find to 2, you'll have in the nearUsers list only two users: the current user and the closest user from the current
        query.setLimit(2);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override  public void done(List<ParseUser> nearUsers, ParseException e) {
                if (e == null) {

                    // avoiding null pointer
                    ParseUser closestUser = ParseUser.getCurrentUser();

                    // set the closestUser to the one that isn't the current user
                    for(int i = 0; i < nearUsers.size(); i++) {
                        if(!nearUsers.get(i).getObjectId().equals(ParseUser.getCurrentUser().getObjectId())) {
                            closestUser = nearUsers.get(i);
                        }
                    }

                    // finding and displaying the distance between the current user and the closest user to him
                    double distance = getCurrentUserLocation().distanceInKilometersTo(closestUser.getParseGeoPoint("Location"));
                    alertDisplayer("We found the closest user from you!", "It's " + closestUser.getUsername() + ". \nYou are " + Math.round (distance * 100.0) / 100.0  + " km from this user.");

                    // showing current user in map
                    showCurrentUserInMap(mMap);

                    // creating a marker in the map showing the closest user to the current user location
                    LatLng closestUserLocation = new LatLng(closestUser.getParseGeoPoint("Location").getLatitude(), closestUser.getParseGeoPoint("Location").getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(closestUserLocation).title(closestUser.getUsername()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                    // zoom the map to the currentUserLocation
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(closestUserLocation, 5));

                } else {
                    Log.d("store", "Error: " + e.getMessage());
                }
            }
        });

        ParseQuery.clearAllCachedResults();

    }

    private void showStoresOnMap(final GoogleMap googleMap){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Stores");
        query.whereExists("Location");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override  public void done(List<ParseObject> stores, ParseException e) {
                if (e == null) {

                    for(int i = 0; i < stores.size(); i++) {

                        LatLng storeLocation = new LatLng(stores.get(i).getParseGeoPoint("Location").getLatitude(), stores.get(i).getParseGeoPoint("Location").getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(storeLocation).title(stores.get(i).getString("Name")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                    }

                } else {
                    Log.d("store", "Error: " + e.getMessage());
                }
            }
        });

        ParseQuery.clearAllCachedResults();

    }

    private void showClosestStore(final GoogleMap googleMap){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Stores");
        query.whereNear("Location", getCurrentUserLocation());
        // setting the limit of near stores to 1, you'll have in the nearStores list only one object: the closest store from the current user
        query.setLimit(1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override  public void done(List<ParseObject> nearStores, ParseException e) {
                if (e == null) {

                    ParseObject closestStore = nearStores.get(0);

                    // showing current user location
                    showCurrentUserInMap(mMap);

                    // finding and displaying the distance between the current user and the closest store to him
                    double distance = getCurrentUserLocation().distanceInKilometersTo(closestStore.getParseGeoPoint("Location"));
                    alertDisplayer("We found the closest store from you!", "It's " + closestStore.getString("Name") + ". \n You are " + Math.round (distance * 100.0) / 100.0  + " km from this store.");

                    // creating a marker in the map showing the closest store to the current user
                    LatLng closestStoreLocation = new LatLng(closestStore.getParseGeoPoint("Location").getLatitude(), closestStore.getParseGeoPoint("Location").getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(closestStoreLocation).title(closestStore.getString("Name")).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                    // zoom the map to the closestStoreLocation
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(closestStoreLocation, 7));

                } else {
                    Log.d("store", "Error: " + e.getMessage());
                }
            }
        });

        ParseQuery.clearAllCachedResults();

    }

    private void alertDisplayer(String title,String message){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog ok = builder.create();
        ok.show();
    }
}