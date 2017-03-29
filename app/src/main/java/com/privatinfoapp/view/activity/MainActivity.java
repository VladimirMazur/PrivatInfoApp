package com.privatinfoapp.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.privatinfoapp.R;
import com.privatinfoapp.util.AppConstants;
import com.privatinfoapp.util.CommonUtils;
import com.privatinfoapp.util.PermissionsRequests;
import com.privatinfoapp.util.PreferencesManager;
import com.privatinfoapp.view.fragment.MainFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragmentNonBackStack(MainFragment.newInstance(), 0, 0);
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }
}
