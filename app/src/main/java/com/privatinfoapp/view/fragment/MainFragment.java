package com.privatinfoapp.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.privatinfoapp.R;
import com.privatinfoapp.factory.DeviceFactory;
import com.privatinfoapp.util.CommonUtils;
import com.privatinfoapp.view.activity.MainActivity;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @OnClick(R.id.btnAtm)
    void onClickATM() {
        ((MainActivity) getActivity()).replaceFragmentWithBackStack(
                PlacingFragment.newInstance(getString(R.string.title_atm), DeviceFactory.TYPE_DEVICES_ATM));
    }

    @OnClick(R.id.btnTerminal)
    void onClickTerminal() {
        ((MainActivity) getActivity()).replaceFragmentWithBackStack(
                PlacingFragment.newInstance(getString(R.string.title_terminals), DeviceFactory.TYPE_DEVICES_TERMINAL));
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_privat_info);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
