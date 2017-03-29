package com.privatinfoapp.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.privatinfoapp.R;
import com.privatinfoapp.mvp.presenter.DevicePresenterImpl;
import com.privatinfoapp.mvp.view.DeviceView;
import com.privatinfoapp.pojo.Devices;
import com.privatinfoapp.util.CommonUtils;
import com.privatinfoapp.view.activity.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacingFragment extends BaseFragment implements DeviceView, OnMapReadyCallback{

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_TYPE = "ARG_TYPE";

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private GoogleMap googleMap;
    private DevicePresenterImpl presenter;

    private List<Devices> devices;
    private Map<String, Devices> markerDevice = new HashMap<>();
    private String title, type;

    public PlacingFragment() {
        // Required empty public constructor
    }

    public static PlacingFragment newInstance(String title, String type) {
        PlacingFragment fragment = new PlacingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            type = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DevicePresenterImpl(this);
        presenter.device(type);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_placing;
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDevices(List<Devices> devices) {
        this.devices = devices;
        showMap(devices);
    }

    @Override
    public void error(String error) {
        ((BaseActivity) getActivity()).showMessage(error);
    }

    /**
     * @param devices
     *
     * Show google map
     */
    private void showMap(List<Devices> devices) {
        if (googleMap == null) {
            final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            moveCameraToCenter(devices);
        }
    }

    /**
     * @param devices
     *
     * Move camera to center screen
     */
    private void moveCameraToCenter(List<Devices> devices) {
        if (devices == null || googleMap == null) {
            return;
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                CommonUtils.getCenterPosition(devices), 10f), 300, null);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.content_item_point_view, null);

                DevicesViewHolder holder = new DevicesViewHolder(view);
                Devices device = markerDevice.get(marker.getId());

                holder.txtDeviceAddress.setText(device.getFullAddressRu());
                holder.txtDetails.setText(device.getPlaceRu());
                holder.txtWorkTime.setText(device.getTw().getMon());
                return view;
            }
        });
        addPins(devices);
    }

    /**
     * @param devices
     *
     * Add pins on map
     */
    private void addPins(List<Devices> devices) {
        if (devices == null || googleMap == null) {
            return;
        }
        for (Devices device : devices) {
            LatLng position = new LatLng(CommonUtils.parseCoordinate(device.getLatitude()),
                    CommonUtils.parseCoordinate(device.getLongitude()));

            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(device.getFullAddressRu())
                    .snippet(device.getPlaceRu()));

            markerDevice.put(marker.getId(), device);
        }
        moveCameraToCenter(devices);
    }

    /**
     * View holder for item pin on map
     */
    class DevicesViewHolder {

        @BindView(R.id.txtDeviceAddress)
        TextView txtDeviceAddress;
        @BindView(R.id.txtDetails)
        TextView txtDetails;
        @BindView(R.id.txtWorkTime)
        TextView txtWorkTime;

        DevicesViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
