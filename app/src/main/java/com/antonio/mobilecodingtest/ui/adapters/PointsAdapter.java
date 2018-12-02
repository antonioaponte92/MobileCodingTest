package com.antonio.mobilecodingtest.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.data.local.PointTable;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder>{
    static final String TAG = PointsAdapter.class.getSimpleName();
    private List<PointTable> pointsList;
    private PointListener listener;
    private Context context;

    public PointsAdapter(PointListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void setData(List<PointTable> pointsList) {
        this.pointsList = pointsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_point, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        holder.title.setText(pointsList.get(pos).getTitle());
        String[] latLng = pointsList.get(pos).getGeocoordinates().split(",");
        float lat = Float.parseFloat(latLng[0]);
        float lng = Float.parseFloat(latLng[1]);
        holder.place = new LatLng(lat,lng);
    }

    @Override
    public int getItemCount() {
        if (pointsList==null) return 0;
        return pointsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback{
        @BindView(R.id.title)       TextView title;
        @BindView(R.id.map)         MapView mapView;
        GoogleMap gMap;
        LatLng place;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mapView.onCreate(null);
            mapView.getMapAsync(this);
            mapView.onResume();
        }

        @OnClick(R.id.card_view)
        void OnClick(){
            listener.onItemClick(pointsList.get(getAdapterPosition()).getIdentifier());
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            try {//Method for set style on Google Maps
                googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                context, R.raw.style_json));
            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Can't find style. Error: ", e);
            }
            gMap = googleMap;
            gMap.getUiSettings().setAllGesturesEnabled(false);
            gMap.addMarker(new MarkerOptions().position(place)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
                    .setDraggable(false);
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,13f));
            gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {                         /**Butterknife do not handle the google maps click events*/
                @Override
                public void onMapClick(LatLng latLng) {
                    listener.onItemClick(pointsList.get(getAdapterPosition()).getIdentifier());
                }
            });
            gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    listener.onItemClick(pointsList.get(getAdapterPosition()).getIdentifier());
                    return true;
                }
            });
        }
    }

    public interface PointListener{
        void onItemClick(String id);
    }
}
