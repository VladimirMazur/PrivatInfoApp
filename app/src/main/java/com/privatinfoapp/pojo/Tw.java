package com.privatinfoapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 28.03.2017.
 */

public class Tw {

    @Expose
    @SerializedName("mon")
    private String mon;
    @Expose
    @SerializedName("tue")
    private String tue;
    @Expose
    @SerializedName("wed")
    private String wed;
    @Expose
    @SerializedName("thu")
    private String thu;
    @Expose
    @SerializedName("fri")
    private String fri;
    @Expose
    @SerializedName("sat")
    private String sat;
    @Expose
    @SerializedName("sun")
    private String sun;
    @Expose
    @SerializedName("hol")
    private String hol;

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTue() {
        return tue;
    }

    public void setTue(String tue) {
        this.tue = tue;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getHol() {
        return hol;
    }

    public void setHol(String hol) {
        this.hol = hol;
    }

}
