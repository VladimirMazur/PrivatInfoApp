package com.privatinfoapp.util;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.text.TextUtils;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;
import com.privatinfoapp.pojo.Devices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Vladimir on 03.03.2017.
 */

public class CommonUtils {

    /**
     * @return location request
     *
     * Build object LocationRequest
     */
    public static LocationRequest getLocationRequest() {
        return new LocationRequest()
                .setInterval(AppConstants.INTERVAL)
                .setFastestInterval(AppConstants.INTERVAL_FASTEST)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    /**
     * @param context
     * @param location
     * @param googleApiClient
     * @return current address
     *
     * Find current city
     */
    public static Address findCurrentCity(Context context, Location location, GoogleApiClient googleApiClient) {
        if (location == null) {
            if (!googleApiClient.isConnecting() &&
                    !googleApiClient.isConnected()) {
                googleApiClient.connect();
            }
            return null;
        }

        try {
            List<Address> addresses = new Geocoder(context, new Locale(AppConstants.LOCAL_RU))
                    .getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                return addresses.get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param doubleAsString
     * @return coordinate
     *
     * Parse coordinates from string to double
     */
    public static Double parseCoordinate(String doubleAsString) {
        if (TextUtils.isEmpty(doubleAsString))
            return 0d;
        Double doubleValue = 0d;
        try {
            doubleValue = Double.parseDouble(doubleAsString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return doubleValue;
    }

    /**
     * @param devices
     * @return lat lon params
     *
     * Get center position of current location
     */
    public static LatLng getCenterPosition(List<Devices> devices) {
        if (devices.size() > 1) {
            double minLat = Double.MAX_VALUE;
            double maxLat = Double.MIN_VALUE;
            double minLon = Double.MAX_VALUE;
            double maxLon = Double.MIN_VALUE;

            for (Devices shop : devices) {
                if (TextUtils.isEmpty(shop.getLatitude())
                        || TextUtils.isEmpty(shop.getLongitude())) {
                    continue;
                }
                double lat = CommonUtils.parseCoordinate(shop.getLatitude());
                double lon = CommonUtils.parseCoordinate(shop.getLongitude());

                if (lat == 0f || lon == 0f) {
                    continue;
                }
                maxLat = Math.max(lat, maxLat);
                minLat = Math.min(lat, minLat);
                maxLon = Math.max(lon, maxLon);
                minLon = Math.min(lon, minLon);
            }
            return new LatLng((maxLat + minLat) / 2, (maxLon + minLon) / 2);

        } else {
            return new LatLng(CommonUtils.parseCoordinate(devices.get(0).getLatitude()),
                    CommonUtils.parseCoordinate(devices.get(0).getLongitude()));
        }
    }
}
