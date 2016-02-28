package se.bolzyk.healhme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.bolzyk.healhme.R;

/**
 * Created by andreasbolzyk on 2016-02-28.
 */
public class MapsFragment extends Fragment {

    public MapsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        return rootView;
    }

}
