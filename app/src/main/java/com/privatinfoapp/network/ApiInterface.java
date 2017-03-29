package com.privatinfoapp.network;

import com.privatinfoapp.pojo.GetPublicInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vladimir on 02.03.2017.
 */

public interface ApiInterface {

    @GET("p24api/infrastructure?json&atm&address=")
    Call<GetPublicInfo> getATM(@Query("city") String city);

    @GET("p24api/infrastructure?json&tso&address=")
    Call<GetPublicInfo> getTerminals(@Query("city") String city);

}
