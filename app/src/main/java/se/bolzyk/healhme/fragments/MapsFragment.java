package se.bolzyk.healhme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import se.bolzyk.healhme.R;

/**
 * Created by andreasbolzyk on 2016-02-28.
 */
public class MapsFragment extends Fragment {


    private SupportMapFragment mapFragment;

    //public MapsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        return rootView;
    }

    /*
    Create the maps view and set some parameters as zoom and position
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.mapview);

        //Set up view first time
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.mapview, mapFragment).commit();

            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {

                    setMapValues(googleMap);



                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(63.816, 20.317), 12));

                }
            });

        //Update view
        } else {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {

                    setMapValues(googleMap);
                }
            });

        }
    }


    private void setMapValues(final GoogleMap googleMap) {

        //iksu
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.818676, 20.318376)).title("Iksu Sport"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.821250, 20.275330)).title("Iksu Plus"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.836387, 20.166371)).title("Iksu Spa"));

        //tenton
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.845019, 20.328696)).title("Tenton"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.827485, 20.253854)).title("Tenton"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(63.857612, 20.313235)).title("Tenton"));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.setMyLocationEnabled(true);

    }
}