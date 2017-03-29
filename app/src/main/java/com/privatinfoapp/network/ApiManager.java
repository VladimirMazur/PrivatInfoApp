package com.privatinfoapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.privatinfoapp.util.AppConstants;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vladimir on 02.03.2017.
 */

public class ApiManager {

    private static ApiInterface api;
    private static Retrofit retrofit;

    private ApiManager() {
    }

    public static ApiInterface getApi() {
        if (api == null) {
            api = getRetrofit().create(ApiInterface.class);
        }
        return api;
    }

    // gson builder
    private static final GsonBuilder gsonBuilder = new GsonBuilder();

    // gson
    private static Gson gson = gsonBuilder.create();

    // get retrofit manager
    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor()).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    // Interceptor Logging
    private static Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
