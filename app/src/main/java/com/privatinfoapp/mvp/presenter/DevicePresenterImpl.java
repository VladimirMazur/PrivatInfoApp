package com.privatinfoapp.mvp.presenter;

import android.content.Context;

import com.privatinfoapp.PrivatInfoApplication;
import com.privatinfoapp.R;
import com.privatinfoapp.factory.DeviceFactory;
import com.privatinfoapp.mvp.view.DeviceView;
import com.privatinfoapp.pojo.GetPublicInfo;
import com.privatinfoapp.util.PreferencesManager;

import java.security.AccessController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vladimir on 28.03.2017.
 */

public class DevicePresenterImpl implements DevicePresenter {

    private DeviceView view;

    public DevicePresenterImpl(DeviceView view) {
        this.view = view;
    }

    @Override
    public void device(String type) {
        if (view != null) {
            view.showProgress();
        }

        String city = PreferencesManager.getInstance().getCurrentCity();

        if (city != null) {
            Call<GetPublicInfo> call = DeviceFactory.getDeviceAPI(type, city);
            if (call != null) {
                call.enqueue(devicesCallback);
            }
        } else {
            view.hideProgress();
            view.error(view.getContext().getString(R.string.error_current_location));
        }
    }

    /**
     * Callback from server for response data of atm or terminals
     */
    private Callback<GetPublicInfo> devicesCallback = new Callback<GetPublicInfo>() {

        @Override
        public void onResponse(Call<GetPublicInfo> call, Response<GetPublicInfo> response) {
            if (response.isSuccessful()) {
                GetPublicInfo albumsResponse = response.body();
                view.setDevices(albumsResponse.getDevices());
            }
            view.hideProgress();
        }

        @Override
        public void onFailure(Call<GetPublicInfo> call, Throwable t) {
            view.hideProgress();
            view.error(t.getMessage());
        }
    };

    @Override
    public void onDestroy() {
        view = null;
    }
}
