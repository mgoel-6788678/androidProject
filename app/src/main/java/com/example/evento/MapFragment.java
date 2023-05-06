package com.example.evento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.My_Map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(googleMap -> {
            MarkerOptions markerOptions = new MarkerOptions();
            CameraUpdate point = CameraUpdateFactory.newLatLngZoom(new LatLng(28.54591761878624, 77.27317245456994), 20); // sets default location to Delhi, India
            googleMap.moveCamera(point);
            googleMap.setOnMapClickListener(latLng -> {
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + ", " + latLng.longitude);
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                googleMap.addMarker(markerOptions);
            });
        });
        return view;
    }
}