package com.privatinfoapp.mvp.view;

import com.privatinfoapp.pojo.Devices;

import java.util.List;

/**
 * Created by Vladimir on 28.03.2017.
 */

public interface DeviceView extends BaseView {

    void showProgress();
    void hideProgress();
    void setDevices(List<Devices> devices);
    void error(String error);
}
