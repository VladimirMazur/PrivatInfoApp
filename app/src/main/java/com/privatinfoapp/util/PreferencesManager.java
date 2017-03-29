package com.privatinfoapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vladimir on 02.03.2017.
 */

public class PreferencesManager {

    private static final String PREFS_CURRENT_CITY = "PREFS_CURRENT_CITY";

    private static final String PREFERENCES_NAME = "SETTINGS";

    private static PreferencesManager instance;
    private final SharedPreferences sharedPreferences;


    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context);
        }
    }

    public static synchronized PreferencesManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeTaskInstance(..) method first.");
        }
        return instance;
    }

    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    /**
     * @return city
     *
     * Get current city
     */
    public String getCurrentCity() {
        return sharedPreferences.getString(PREFS_CURRENT_CITY, null);
    }

    /**
     * @param city
     *
     * Set current city
     */
    public void setCurrentCity(String city) {
        sharedPreferences.edit().putString(PREFS_CURRENT_CITY, city).apply();
    }
}
