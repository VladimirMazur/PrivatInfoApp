package com.privatinfoapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Vladimir on 02.03.2017.
 */

public class NetworkUtil {

    /**
     * @param mContext
     * @return is network
     *
     * Check internet connection
     */
    public static boolean isNetworkConnected(Context mContext) {
        if (mContext != null) {
            ConnectivityManager manager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager.getActiveNetworkInfo() != null) {
                return (manager.getActiveNetworkInfo().isAvailable() && manager
                        .getActiveNetworkInfo().isConnected());
            }
        }
        return false;
    }
}
