package com.privatinfoapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.privatinfoapp.R;
import com.privatinfoapp.mvp.view.BaseView;
import com.privatinfoapp.view.CustomToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Vladimir on 24.03.2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    @BindView(R.id.custom_toolbar)
    CustomToolbar toolbar;

    public Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setCustomToolbar();
    }

    /**
     * Set components for custom toolbar
     */
    private void setCustomToolbar() {
        toolbar.setTitle(getTitle());
        toolbar.setListenerButtonBack(R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    protected abstract int getFragmentLayout();
    protected abstract String getTitle();
}
