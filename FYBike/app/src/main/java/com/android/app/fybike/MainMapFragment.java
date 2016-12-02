package com.android.app.fybike;


import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import Map.MapHandler;

public class MainMapFragment extends Fragment implements OnMapReadyCallback{

    private SupportMapFragment m_map;
    GoogleMap mGoogleMap;
    public Location currentLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText search = (EditText) getActivity().findViewById(R.id.txtSearch);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });
        m_map = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        m_map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(10.7956526, 106.6772461), 17));
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_place)).anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(10.7956526, 106.6772461)));
        try {
            googleMap.setMyLocationEnabled(true);
            mGoogleMap = googleMap;
            GetShopList();

        }catch(SecurityException se) {
            //show something
        }
    }

    public void GetShopList()
    {
        LocationManager locationManager = (LocationManager)
                    getActivity().getSystemService(getContext().LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location location = locationManager.getLastKnownLocation(locationManager
                            .getBestProvider(criteria, false));
                currentLocation = location;

                MapHandler mapHandler = new MapHandler(mGoogleMap, getContext());
                mapHandler.execute(location.getLongitude(), location.getLatitude() );

            }
        }

    }
}
