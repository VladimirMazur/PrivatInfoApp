package com.privatinfoapp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.privatinfoapp.R;
import com.privatinfoapp.util.AppConstants;
import com.privatinfoapp.util.CommonUtils;
import com.privatinfoapp.util.PermissionsRequests;
import com.privatinfoapp.util.PreferencesManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, PermissionsRequests.OnCancelPermission {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildGoogleApiClient();
    }

    /**
     * Build google api client
     */
    protected void buildGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Connect the client.
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        // Disconnecting the client invalidates it.
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    /**
     * Start location update
     */
    private void startLocationUpdates() {
        if (!googleApiClient.isConnected()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionsRequests.requestPermissionForLocation(this, this);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, CommonUtils.getLocationRequest(), this);
    }

    /**
     * Stop location update
     */
    private void stopLocationUpdates() {
        if (!googleApiClient.isConnected()) {
            return;
        }
        LocationServices.FusedLocationApi
                .removeLocationUpdates(googleApiClient, this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, getString(R.string.log_connection_has_been_suspend));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, getString(R.string.log_connection_has_failed));
    }

    @Override
    public void onLocationChanged(Location location) {
        setCurrentCity(location);
    }

    /**
     * @param location
     *
     * Set current city to preferences
     */
    private void setCurrentCity(Location location) {
        Address city = CommonUtils.findCurrentCity(getApplicationContext(), location, googleApiClient);
        if (city != null) {
            PreferencesManager.getInstance().setCurrentCity(city.getLocality());
        }
        navigateMainActivity();
    }

    /**
     * Navigate to MainActivity
     */
    private void navigateMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionsRequests.LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.onConnected(null);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCancelPermission() {
        navigateMainActivity();
    }
}
