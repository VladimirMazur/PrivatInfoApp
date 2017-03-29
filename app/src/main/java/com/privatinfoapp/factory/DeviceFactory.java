package com.privatinfoapp.factory;

import com.privatinfoapp.network.ApiManager;
import com.privatinfoapp.pojo.GetPublicInfo;

import retrofit2.Call;

/**
 * Created by Vladimir on 28.03.2017.
 */

public class DeviceFactory {

    public static final String TYPE_DEVICES_ATM = "TYPE_DEVICES_ATM";
    public static final String TYPE_DEVICES_TERMINAL = "TYPE_DEVICES_TERMINAL";

    /**
     * @param type
     * @param city
     * @return request for devices
     *
     * Get devices API
     */
    public static Call<GetPublicInfo> getDeviceAPI(String type, String city) {
        if (type.equalsIgnoreCase(TYPE_DEVICES_ATM)) {
            return ApiManager.getApi().getATM(city);
        } else if (type.equalsIgnoreCase(TYPE_DEVICES_TERMINAL)) {
            return ApiManager.getApi().getTerminals(city);
        }
        return null;
    }
}
