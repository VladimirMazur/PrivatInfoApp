package com.privatinfoapp;

import android.app.Application;
import android.content.Context;

import com.privatinfoapp.util.PreferencesManager;

/**
 * Created by Vladimir on 28.03.2017.
 */

public class PrivatInfoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // init preferences manager
        PreferencesManager.initializeInstance(getApplicationContext());
    }
}
