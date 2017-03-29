package com.privatinfoapp.util;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.privatinfoapp.R;

/**
 * Created by Vladimir on 03.03.2017.
 */

public class PermissionsRequests {

    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * @param activity
     * @param listener
     *
     * Request add permission for location
     */
    public static void requestPermissionForLocation(final Activity activity, OnCancelPermission listener) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)) {
            showLocationDialog(activity, listener);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * @param activity
     * @param listener
     *
     * Show AlertDialog for permission
     */
    private static void showLocationDialog(final Activity activity, final OnCancelPermission listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setMessage(activity.getString(R.string.alert_access_location))
                .setPositiveButton(activity.getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity, new String[]{
                                                Manifest.permission.ACCESS_COARSE_LOCATION},
                                        LOCATION_PERMISSION_REQUEST_CODE);
                            }
                        })
                .setNegativeButton(activity.getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onCancelPermission();
                                dialog.dismiss();
                            }
                        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Interface for cancel permission
     */
    public interface OnCancelPermission {
        void onCancelPermission();
    }
}
