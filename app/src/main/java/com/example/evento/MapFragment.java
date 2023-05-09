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

import java.util.ArrayList;

public class MapFragment extends Fragment {
    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;
    LatLng LHC = new LatLng(28.545433988075278, 77.27317419220343);
    LatLng OldAcademic = new LatLng(28.544518423567478, 77.2725732284581);
    LatLng Library = new LatLng(28.54394879929117, 77.2724934010881);
    LatLng RnD = new LatLng(28.544024673895382, 77.27165551445275);
    LatLng Playground = new LatLng(28.547476347672564, 77.27293284712653);
    LatLng BasketBallCourt = new LatLng(28.548241560051505, 77.27327942461254);
    LatLng Canteen = new LatLng(28.54620466452314, 77.27316002958774);
    LatLng SportsBlock = new LatLng(28.547463591428443, 77.27251554551582);

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

            locationArrayList = new ArrayList<>();
            locationArrayList.add(LHC);
            locationArrayList.add(RnD);
            locationArrayList.add(Library);
            // inside on map ready method
            // we will be displaying all our markers.
            // for adding markers we are running for loop and
            // inside that we are drawing marker on our map.
            for (int i = 0; i < locationArrayList.size(); i++) {

                // below line is use to add marker to each location of our array list.
                googleMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));

                // below line is use to zoom our camera on map.
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                // below line is use to move our camera to the specific location.
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
            }
//            googleMap.setOnMapClickListener(latLng -> {
//                markerOptions.position(latLng);
//                markerOptions.title(latLng.latitude + ", " + latLng.longitude);
//                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
//                googleMap.addMarker(markerOptions);
//            });
        });
        return view;
    }
}