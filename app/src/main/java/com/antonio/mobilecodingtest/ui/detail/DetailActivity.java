package com.antonio.mobilecodingtest.ui.detail;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.commons.BaseActivity;
import com.antonio.mobilecodingtest.data.models.PointDetails;
import com.antonio.mobilecodingtest.ui.detail.mvp.DetailContract;
import com.antonio.mobilecodingtest.ui.detail.mvp.DetailPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailContract.View, OnMapReadyCallback{
    static final String TAG = DetailActivity.class.getSimpleName();

    @BindView(R.id.title)           TextView title;
    @BindView(R.id.address)         TextView address;
    @BindView(R.id.transport)       TextView transport;
    @BindView(R.id.phone)           TextView phone;
    @BindView(R.id.email)           TextView email;
    @BindView(R.id.progressBar)     ProgressBar progressBar;
    @BindView(R.id.dataLayout)      LinearLayout dataLayout;
    @BindView(R.id.map)             MapView mapView;
    GoogleMap gMap;
    LatLng place;

    DetailPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        if (getIntent().getExtras()!=null){
            presenter = new DetailPresenter(this,getBaseContext());
            presenter.getData(getIntent().getStringExtra("id"));
            mapView.onCreate(savedInstanceState);
        }
    }

    @OnClick({R.id.back})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    /**
     * The validation of the fields is done assuming that null is a string because
     * it comes from the Endpoint like a String
     */
    @Override
    public void showData(PointDetails data) {
        dataLayout.setVisibility(View.VISIBLE);
        title.setText(data.getTitle());
        address.setText(data.getAddress().equals("null") ? getString(R.string.no_info) :data.getAddress());
        transport.setText(data.getTransport().equals("null") ? getString(R.string.no_info):data.getTransport());
        phone.setText(data.getPhone().equals("null") ? getString(R.string.no_info):data.getPhone());
        email.setText(data.getEmail().equals("null") ? getString(R.string.no_info):data.getEmail());
        ExpandableTextView expTv1 = findViewById(R.id.expand_text_view)
                .findViewById(R.id.expand_text_view);
        expTv1.setText(data.getDescription());
        String[] latLng = data.getGeocoordinates().split(",");
        place = new LatLng(Float.parseFloat(latLng[0]),Float.parseFloat(latLng[1]));
        mapView.getMapAsync(this);
    }

    @Override
    public void showNoResult() {

    }

    @Override
    public void showError(String reason) {

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
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {//Method for set style on Google Maps
            googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        gMap = googleMap;
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        gMap.addMarker(new MarkerOptions().position(place)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
                .setDraggable(false);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,13f));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,13f));
    }
}
