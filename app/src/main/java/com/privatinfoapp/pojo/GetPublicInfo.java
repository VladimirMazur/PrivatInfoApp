package com.privatinfoapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vladimir on 28.03.2017.
 */

public class GetPublicInfo {

    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("devices")
    private ArrayList<Devices> devices;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Devices> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Devices> devices) {
        this.devices = devices;
    }
}
